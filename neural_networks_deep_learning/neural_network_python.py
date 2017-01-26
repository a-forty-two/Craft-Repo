class neural_network:
    '''
    neural_network_python.py

    A module to implement a feedfoward neural network in python.  
    Included are static methods for feeding the network forward,
    backpropogating and calculating the gradients.  The code was built 
    to use in conjunction with numpy and numpy matrices.

    Recquired Libaries:
    numpy
    random

    '''   
    import random
    import numpy as np
    import pandas as pd

    @staticmethod    
    def transfer_function(z,fun):
        if fun == 'sigmoid': 
            return 1.0/(1.0+np.exp(-z))
        elif fun == 'relu': 
            return np.maximum(.0001, z)
        elif fun == 'tanh': 
            return np.tanh(z) #return (np.exp(z) - np.exp(-z)) / (np.exp(z) + np.exp(-z))
        else: 
            return 1.0/(1.0+np.exp(-z))
    
    @staticmethod
    def transfer_function_prime(z, fun):
        if fun == 'sigmoid':
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),(1-neural_network.transfer_function(z, 'sigmoid')))
        elif fun == 'relu':
            return np.where(z<0, .0001, 1) 
        elif fun == 'tanh':
            return 1.0 - np.square(np.tanh(z))
            #1.0 - np.square(neural_network.transfer_function(z,'tanh'))
        else: 
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),(1-neural_network.transfer_function(z, 'sigmoid')))
        
    @staticmethod
    def cost_function(y,a,fun):
        if fun == 'quadratic_cost': 
            return np.sum(np.square((y - a)))
        elif fun == 'cross_entropy': 
            return np.sum(np.nan_to_num(np.multiply(-y,np.log(a))-np.multiply((1-y),(np.log(1-a)))))
        else: 
            return np.sum(np.square((y - a)))
        
    @staticmethod
    def feedforward(a,w,b,transfer_function):
        z = np.dot(w,a)+b
        a = neural_network.transfer_function(z, transfer_function)
        return z,a
        
    @staticmethod
    def get_error(y, yhat, intermediate_z, transfer_function, cost_function):
        if cost_function == 'quadratic_cost':
            return np.multiply((yhat - y),neural_network.transfer_function_prime(intermediate_z[-1], transfer_function))
        elif cost_function == 'cross_entropy':
            return (y - yhat)

    @staticmethod
    def backpropogation(mini,delta,size,intermediate_z,transfer_function,weights,batches_y,activations,cost_function):
        delta[-1] = neural_network.get_error(batches_y[mini],activations[-1],intermediate_z,transfer_function,cost_function)
        for i in range(2,size):
            sp = neural_network.transfer_function(intermediate_z[i-1], transfer_function)
            error = np.dot(weights[i].T, delta[i])
            delta_value = np.multiply(error,sp)
            delta[i-1] = delta_value
        return delta
    
    @staticmethod
    def weight_bias_update(size,delta,activations,weights,bias,eta):
        for i in range(1,size):
            gradient = np.dot(delta[i], activations[i-1].T)
            weights[i] = weights[i] - eta * gradient
            bias[i] = bias[i] - eta * np.mean(delta[i],1)
        return weights,bias
    
    def __init__(self, layers, x, y, eta, transfer_function, cost_function, epochs, training_size, batches):
        
        self.size = len(layers)
        self.layers = layers
        self.eta = .1
        self.transfer_function = transfer_function
        self.cost_function = cost_function
        self.epochs = epochs
        self.training_size = training_size
        self.batches = batches
        self.x = x
        self.y = y
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
        self.bias = []
        self.weights = []
        self.intermediate_z = []
        self.activations = []
        self.delta = []
        self.intermediate_z_test = []
        self.activations_test = []
    
    def __weight_init__(self):
        for i in range(0,self.size):
            if i == 0: 
                self.weights.append( np.zeros( (1,1) ) )
            else: 
                self.weights.append(np.random.randn(self.layers[i], self.layers[i-1]))
    
    def __bias_init__(self):
        for i in range(0,self.size):
            if i == 0: 
                self.bias.append(np.zeros((1,1)))
            else: 
                self.bias.append(np.random.randn(self.layers[i], 1 ))
    
    def __delta_init__(self):
        for i in range(0,self.size):
            self.delta.append((np.zeros((1,1))))
    
    def __activations_init__(self):
        for i in range(0,self.size):
            self.activations.append((np.zeros((1,1))))
            self.activations_test.append((np.zeros((1,1))))
    
    def __z_intermediate_init__(self):
        for i in range(0,self.size):
            self.intermediate_z.append((np.zeros((1,1))))
            self.intermediate_z_test.append((np.zeros((1,1))))
            
    def __make_batches__(self):
        for i in np.array_split(self.train_x,self.batches,1): 
            self.batches_x.append(i)
        for i in np.array_split(self.train_y,self.batches,1): 
            self.batches_y.append(i)                            
        
    def train_network(self):
        
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
                                                            self.activations, self.cost_function)
    
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