import numpy as np
import math as math
import pandas as pd

# xor data
x = np.matrix('0,1,0,1;1,1,0,0')
y = np.matrix('0,1,0,1')
layers = [2,5,1]

# ames housing dataset
train = pd.read_csv('train.csv')
keep = ['LotArea', 'OverallQual', 'GarageCars', 'FullBath', 'GrLivArea', 'TotalBsmtSF', 'SalePrice']
for i in train.columns:
    if i in keep: pass
    else: train = train.drop(i,1)
for i in train.columns:
    train[i] = (train[i] - train[i].min()) / (train[i].max() - train[i].min())
y = np.matrix(train['SalePrice'])
x = np.matrix(train.drop('SalePrice',1)).T
layers = [6,3,1]

class neural_network:
    
    import random
    import numpy as np
    import pandas as pd

    # static methods for the transfer function and its prime
    @staticmethod    
    def transfer_function(z,fun):
        if fun == 'sigmoid':
            return 1.0/(1.0+np.exp(-z))
        elif fun == 'relu':
            return np.maximum(.0001, z)  
        else:
            return np.maximum(.0001, z) 
    
    @staticmethod
    def transfer_function_prime(z, fun):
        if fun == 'sigmoid':
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
        elif fun == 'relu':
            return np.where(z<0, 0, 1)  
        else:
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
        
    
    @staticmethod
    def cost_function(y,a,fun):
        
        if fun == 'rmse':
            return np.sum(y - a)**2
        else:
            return np.sum(y - a)**2
        
    def __init__(self, layers, x, y, eta, transfer_function, cost_function, epochs):
        
        # network hyperparams
        self.size = len(layers)
        self.layers = layers
        self.eta = .1
        self.transfer_function = transfer_function
        self.cost_function = cost_function
        self.epochs = epochs
        
        # data to train
        self.x = x
        self.y = y
        
        # model paramaters
        self.bias = []
        self.weights = []
        self.intermediate_z = []
        self.activations = []
        self.delta = []
    
    # initialize our weights, we use a list to store our matrices
    def __weight_init__(self):
        
        for i in range(0,self.size):
            
            if i == 0:
                self.weights.append( np.zeros( (1,1) ) )
            else:
                w = np.random.randn(self.layers[i], self.layers[i-1])
                self.weights.append(w)
    
    # initialize our bias, we use a list to store the matrices
    def __bias_init__(self):
        
        for i in range(0,self.size):
            
            if i == 0:
                self.bias.append(np.zeros( (1,1) ) )
            else:
                b = np.random.randn(self.layers[i], 1 )
                self.bias.append(b)
    
    # initialize a blank delta list, so we can continually update the values
    def __delta_init__(self):
        
        for i in range(0,self.size):
            delta = (np.zeros((1,1)))
            self.delta.append(delta)
    
    # initialize a blank activations list, so we can continually update the values
    def __activations_init__(self):
        
        for i in range(0,self.size):
            a = (np.zeros((1,1)))
            self.activations.append(a)
    
    # initialize a blank intermediate z score matrix, so values can continually be updated
    def __z_intermediate_init__(self):
        
        for i in range(0,self.size):
            z = (np.zeros((1,1)))
            self.intermediate_z.append(z)
        
    
    # begin feedforward
    def feedforward(self):
        
        for i in range(0,self.size):
            
            if i == 0:
                self.intermediate_z.append(np.zeros((1,1)))
                self.activations[0] = x
            else:
                z = np.dot(self.weights[i], self.activations[i-1])+self.bias[i]
                a = neural_network.transfer_function(z, self.transfer_function)
                self.activations[i] = a
                self.intermediate_z[i] = z
    
    # backpropogate our error
    def backprop(self):

        error = (self.activations[-1] - self.y)
        sp = neural_network.transfer_function(self.intermediate_z[-1], self.transfer_function)
        delta = np.multiply(error,sp)
        self.delta[-1] = delta
        
        for i in range(2,self.size):
            l = i - 1
            z = self.intermediate_z[l]
            sp = neural_network.transfer_function_prime(z, self.transfer_function)
            
            error = np.dot(self.weights[l+1].T, self.delta[l+1])
            delta = np.multiply(error,sp)
            
            self.delta[l] = delta
    
    # update our weights
    def weight_bias_update(self):
        
        for i in range(1,self.size):
            l = i
            gradient = np.dot(self.delta[l], self.activations[l-1].T)
            self.weights[l] = self.weights[l] - self.eta * gradient
            self.bias[l] = self.bias[l] - self.eta * np.mean(self.delta[l],1)
            
    # function to run the model
    def train_network(self):
        for i in range(1,self.epochs):
            self.feedforward()
            self.backprop()
            self.weight_bias_update()
            
            if i%1000 == 0:
                yhat = pd.DataFrame(self.activations[-1].T)
                yact = pd.DataFrame(self.y.T)
                frames = [yhat,yact]
                test = pd.concat(frames, axis = 1)
                test.columns = ['yhat', 'y']

                print 'Epoch: ', i, 'Cost: ', neural_network.cost_function(self.activations[-1], y, \
                                                         self.cost_function), \
                ' Correlation: ', np.corrcoef(test['yhat'], test['y'])[0,1]