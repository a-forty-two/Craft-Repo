import numpy as np
import math as math
import pandas as pd

# xor data
x = np.matrix('0,1,0,1;1,1,0,0')
y = np.matrix('0,1,0,1')
layers = [2,5,1]

# ames housing dataset
train = pd.read_csv('train.csv')
keep = ['LotArea', 'OverallQual', 'GarageCars', 'FullBath', 'GrLivArea', 'TotalBsmtSF', 'SalePrice', \
       'LotFrontage', 'OverallCond', 'MSSubClass', 'YearBuilt']
for i in train.columns:
    if i in keep: pass
    else: train = train.drop(i,1)
train = train.fillna(train.mean())
for i in train.columns:
    train[i] = (train[i] - train[i].min()) / (train[i].max() - train[i].min())
y = np.matrix(train['SalePrice'])
x = np.matrix(train.drop('SalePrice',1)).T
layers = [10,25,1]


class neural_network:
    
    # libraries to use
    import random
    import numpy as np

    # declare static methods here

    @staticmethod    
    def transfer_function(z,fun):

        '''
        applies a transfer function to the input z
        
        sigmoid: 1/(1+e^-z)
        tanh: (e^z - e^-z) / (e^z + e^-z)
        relu: the max of z and .0001, note epsilon is uses to eliminate 0s
        '''

        if fun == 'sigmoid': return 1.0/(1.0+np.exp(-z))
        elif fun == 'relu': return np.maximum(.0001, z)
        elif fun == 'tanh': return np.tanh(z) #return (np.exp(z) - np.exp(-z)) / (np.exp(z) + np.exp(-z))
        else: return np.maximum(.0001, z) 
    
    @staticmethod
    def transfer_function_prime(z, fun):

        '''
        applies the prime of the transfer function to the input z

        sigmoid: sigmoid(z) * (1-sigmoid(z))
        tanh: 1 - tanh(z)^2
        relu: - .0001 if z is less than 0, 1 if z is greater then 0, the epsilon is used to minimize the 0 errors
        '''
        if fun == 'sigmoid':return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
        elif fun == 'relu':return np.where(z<0, .0001, 1) 
        elif fun == 'tanh':return 1.0 - np.square(np.tanh(z))
                #1.0 - np.square(neural_network.transfer_function(z,'tanh'))
        else: return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
        
    @staticmethod
    def cost_function(y,a,fun):

        '''
        Calculate some cost using the y and a(yhat) values

        rmse: (y-a)^2
        '''
        if fun == 'rmse': return np.sum(np.square((y - a)))
        else: return np.sum(np.square((y - a)))
        
    @staticmethod
    def feedforward(a,w,b,transfer_function):

        '''
        Calculate activaitons and intermediate z scores for a neural network
        '''
        z = np.dot(w,a)+b
        a = neural_network.transfer_function(z, transfer_function)
        return z,a
        
    @staticmethod
    def get_error(y, yhat, intermediate_z, transfer_function):

        '''
        Returns the error of the last layer of the neural network
        '''
        return np.multiply((yhat - y),neural_network.transfer_function_prime(intermediate_z[-1], transfer_function))

    @staticmethod
    def backpropogation(mini,delta,size,intermediate_z,transfer_function,weights,batches_y,activations):

        '''
        Backpropogates error through a neural network
        '''
        delta[-1] = neural_network.get_error(batches_y[mini],activations[-1],intermediate_z,transfer_function)
        for i in range(2,size):
            sp = neural_network.transfer_function(intermediate_z[i-1], transfer_function)
            error = np.dot(weights[i].T, delta[i])
            delta_value = np.multiply(error,sp)
            delta[i-1] = delta_value
        return delta
    
    @staticmethod
    def weight_bias_update(size,delta,activations,weights,bias,eta):

        '''
        Updates the weights and bias for a neural network using the given deltas
        '''
        for i in range(1,size):
            gradient = np.dot(delta[i], activations[i-1].T)
            weights[i] = weights[i] - eta * gradient
            bias[i] = bias[i] - eta * np.mean(delta[i],1)
        return weights,bias
    
    def __init__(self, layers, x, y, eta, transfer_function, cost_function, epochs, training_size, batches):

        '''
        Initialize a neural network object
        '''
        # network hyperparams
        self.size = len(layers)
        self.layers = layers
        self.eta = .1
        self.transfer_function = transfer_function
        self.cost_function = cost_function
        self.epochs = epochs
        self.training_size = training_size
        self.batches = batches
        
        # data to train
        self.x = x
        self.y = y
        
        # training, test
        split = int(self.x.shape[1] * self.training_size)
        indices = np.random.permutation(self.x.shape[1])
        train_ind = indices[:split]
        test_ind = indices[split:]
        train_x, test_x = self.x[:,train_ind], x[:,test_ind]
        train_y, test_y = self.y[:,train_ind], y[:,test_ind]
        self.train_x = train_x
        self.train_y = train_y
        self.test_x = test_x
        self.test_y = test_y
        
        self.batches_x = []
        self.batches_y = []
        
        # model paramaters
        self.bias = []
        self.weights = []
        self.intermediate_z = []
        self.activations = []
        self.delta = []
        
        # train params
        self.intermediate_z_test = []
        self.activations_test = []
    
    def __weight_init__(self):

        '''
        Initialize a list to store weights in.  This is done so weights can be easily
        added at indices that align with their layer.  For instance, weights[2] can then be
        used to store the weights for the third layer (python index at 0).  This is done
        for the weights,bias,deltas, z values and activations.  This also allows for 
        the end user to easily access the network paramaters.
        '''

        for i in range(0,self.size):
            if i == 0: self.weights.append( np.zeros( (1,1) ) )
            else: self.weights.append(np.random.randn(self.layers[i], self.layers[i-1]))
    
    def __bias_init__(self):
        '''
        See comments in the __weight_init__ method.
        '''

        for i in range(0,self.size):
            if i == 0: self.bias.append(np.zeros((1,1)))
            else: self.bias.append(np.random.randn(self.layers[i], 1 ))
    
    def __delta_init__(self):

        '''
        See comments in the __weight_init__ method.
        '''

        for i in range(0,self.size):
            self.delta.append((np.zeros((1,1))))
    
    def __activations_init__(self):

        '''
        See comments in the __weight_init__ method.
        '''

        for i in range(0,self.size):
            self.activations.append((np.zeros((1,1))))
            self.activations_test.append((np.zeros((1,1))))
    
    def __z_intermediate_init__(self):

        '''
        See comments in the __weight_init__ method.
        '''

        for i in range(0,self.size):
            self.intermediate_z.append((np.zeros((1,1))))
            self.intermediate_z_test.append((np.zeros((1,1))))
            
    def __make_batches__(self):

        '''
        Creates mini batches to train off
        '''

        for i in np.array_split(self.train_x,self.batches,1): self.batches_x.append(i)
        for i in np.array_split(self.train_y,self.batches,1): self.batches_y.append(i)                            
        
    def train_network(self):

        '''
        train a neural network using the above methods and the instance of 
        class neural_network
        '''

        for epoch in range(1,self.epochs): 
            
            for i,batch in enumerate(self.batches_x):
                
                self.activations[0] = batch 
                
                for l in range(1,self.size):
                    z,a = neural_network.feedforward(self.activations[l-1], self.weights[l], self.bias[l], \
                                                     self.transfer_function)
                    self.activations[l] = a
                    self.intermediate_z[l] = z

                self.delta = neural_network.backpropogation(i,self.delta,self.size,self.intermediate_z,\
                                                            self.transfer_function,self.weights,self.batches_y,\
                                                            self.activations)
    
                self.weights,self.bias = neural_network.weight_bias_update(self.size,self.delta,self.activations,\
                                                                 self.weights,self.bias,self.eta)
            if epoch%1000 == 0:
                self.activations_test[0] = self.test_x
                for l in range(1,self.size):
                    _ ,a_ = neural_network.feedforward(self.activations_test[l-1], \
                                                     self.weights[l], self.bias[l], self.transfer_function)
                    self.activations_test[l] = a_
                    
                print 'Epoch:',epoch,'Cost:', \
                    neural_network.cost_function(self.batches_y[i],self.activations[i],self.cost_function), \
                    np.corrcoef(self.batches_y[i],self.activations[i])[0,1], ' | ', \
                    neural_network.cost_function(self.test_y,self.activations_test[-1],self.cost_function),\
                    np.corrcoef(self.batches_y[i],self.activations[i])[0,1]