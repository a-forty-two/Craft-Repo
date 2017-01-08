class node:
        
    @staticmethod 
    def heuristic_function(state, goal):
        
        h_cost = 0
        for x in zip(state, goal):
            if x[0] != x[1]:
                h_cost += 1
            else:
                continue
        return h_cost
    
    @staticmethod 
    def manhattan_distance(state, goal):
        
        h_cost = 0
        for x in goal:
            h_cost += abs(goal.index(x) - state.index(x))
        return h_cost
    
    @staticmethod
    def heuristic_function_self_def():
        pass
        # placeholder for a personal defined heuristic function
        # likely some distance cost, such as cosine, jacard, 
        # euclidean, pearson.
        
    @staticmethod 
    def get_a_star_cost(h_cost, q_cost):
        return h_cost + q_cost
 
    def __init__(self, key, state, goal_state, parent_node, left_child, right_child):
        
        self.key = key
        self.state = state
        self.parent_node = parent_node
        self.left_child = left_child
        self.right_child = right_child
        self.goal_state = goal_state
        self.new_state = state
        
    def get_moves(self):
        
        self.records = [[],[]]
        
        if self.state.index(0) == 8:
            self.records[0].append('right') #move
            self.records[1].append(self.state[7]) # cost
                        
        elif self.state.index(0) == 0:
            self.records[0].append('left') # move
            self.records[1].append(self.state[1]) # cost
            
        else:
            self.records[0].extend(('left','right')) #moves
            self.records[1].extend((self.state[self.state.index(0)+1],self.state[self.state.index(0)-1])) #cost
    
    def get_heuristic_cost(self, heuristic_function):
        
        if heuristic_function == 'manhattan_distance':
            self.records.append(node.manhattan_distance(self.state,self.goal_state))  
            
        elif heuristic_function == 'heuristic_function':
            self.records.append(node.heuristic_function(self.state,self.goal_state))
            
        else:
            print 'There is something wrong with the distance function you entered.'
            
    def move_piece(self, move):
                
        new_node = self.state[:] # the [:] creates a new copy, while eliminating the pointer reference
        
        if move in self.records[0]:
            
            if move == 'left':
                zero_idx = new_node.index(0)
                item_idx = new_node.index(0) + 1
                new_item = new_node[zero_idx]
                new_zero_item = new_node[item_idx]
                new_node[zero_idx] = new_zero_item
                new_node[item_idx] = new_item
                return new_node
                
            else:
                zero_idx = new_node.index(0)
                item_idx = new_node.index(0) - 1
                new_item = new_node[zero_idx]
                new_zero_item = new_node[item_idx]
                new_node[zero_idx] = new_zero_item
                new_node[item_idx] = new_item
                return new_node
                
        else:
            return 'In the given state a {enter_move} is not possible.'.format(enter_move = move)
             
class stack:

    def __init__(self, init_state):
        self.init_state = init_state
        self.lst = []
    
    def add(self, key):
        self.lst.append(key)
    
    def pop(self):
        return self.lst[-1]
        
    def reset_state(self, key):
        self.init_state = key

class queue:
    
    def __init__(self, init_state):
        self.init_state = init_state
        self.lst = []
    
    def add(self, key):
        self.lst.append(key)
    
    def tail(self):
        return self.lst[0]
        
    def reset_state(self, key):
        self.init_state = key
        
class searchTreeSolver:
        
    def __init__(self, node_init):
        
        self.root = node_init.state
        self.current_node = node_init
        self.structure = {}
        self.structure[0] = node_init.state
        self.key_counter = 1
        self.goal_state = node_init.goal_state
        
    def breadth_first(self):  
        
        if self.current_node.state != self.current_node.goal_state:
                        
            move =  self.current_node.records[0] \
                [self.current_node.records[1].index(min(self.current_node.records[1]))]     
            new_state = self.current_node.move_piece(move)          
            new_node = node(self.key_counter,new_state,self.goal_state,self.key_counter - 1,None,None)
            self.current_node = new_node
            self.structure[self.key_counter] = new_state
            self.key_counter += 1
            
        else:
            return 'Omg this fucking things worked!'

easy = [1,3,4,8,6,2,7,0,5]      
goal = [1,2,3,8,0,4,7,6,5] 

test_node = node(0,easy,goal,None,None,None)
test_node.get_moves()
test_node.get_heuristic_cost('manhattan_distance')
test_tree = searchTreeSolver(test_node)
test_tree.breadth_first()
test_tree.structure

import pandas as pd
for k,v in test_tree.structure.items():
    print '\n'
    print 'Move Count: ', k, '\n'
    print pd.DataFrame([[v[0],v[1],v[2]], \
                        [v[3],v[4],v[5]], \
                        [v[6],v[7],v[8]]]).to_string(index = False, header = False)


'''
Returns

Move Count:  0 

1  3  4
8  6  2
7  0  5


Move Count:  1 

1  3  4
8  6  2
7  5  0
'''







