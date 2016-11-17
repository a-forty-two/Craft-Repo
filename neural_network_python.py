class neural_network:
    
    import random
    import numpy as np

    # static methods for the transfer function and its prime
    @staticmethod    
    def transfer_function(z,fun):
        if fun == 'sigmoid':
            return 1.0/(1.0+np.exp(-z))
        else:
            return 1.0/(1.0+np.exp(-z))
    
    @staticmethod
    def transfer_function_prime(z, fun):
        if fun == 'sigmoid':
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
        else:
            return np.multiply(neural_network.transfer_function(z, 'sigmoid'),\
                               (1-neural_network.transfer_function(z, 'sigmoid')))
    
    @staticmethod
    def cost_function(y,a,fun):
        
        if fun == 'rmse':
            return np.sum(y - a)
        else:
            return np.sum(y - a)
        
    def __init__(self, layers, x, y):
        
        # network hyperparams
        self.size = len(layers)
        self.layers = layers
        
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
                a = neural_network.transfer_function(z, fun = 'sigmoid')
                self.activations[i] = a
                self.intermediate_z[i] = z
            
            print 'Cost: ', neural_network.cost_function(self.activations[-1], y, 'rmse')
    
    # backpropogate our error
    def backprop(self):

        error = (self.activations[-1] - self.y)
        sp = neural_network.transfer_function(self.intermediate_z[-1], fun = 'sigmoid')
        delta = np.multiply(error,sp)
        self.delta[-1] = delta
        
        for i in range(2,self.size):
            l = i - 1
            z = self.intermediate_z[l]
            sp = neural_network.transfer_function_prime(z, fun = 'sigmoid')
            
            error = np.dot(self.weights[l+1].T, self.delta[l+1])
            delta = np.multiply(error,sp)
            
            self.delta[l] = delta
    
    # update our weights
    def weight_bias_update(self):
        
        for i in range(1,self.size):
            l = i
            gradient = np.dot(self.delta[l], self.activations[l-1].T)
            self.weights[l] = self.weights[l] - .1 * gradient
