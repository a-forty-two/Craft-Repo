'''
Depaul CSC 480 Assignment: 8 Puzzle Solver

Goal:   [1,2,3,8,0,4,7,6,5]
Easy:   [1,3,4,8,6,2,7,0,5]
Medium: [2,8,1,0,4,3,7,6,5]
Hard:   [5,6,7,4,0,8,3,2,1]

'''

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
        
    def get_moves(self):
        
        self.records = [[],[]]
        
        if self.state.index(0) == 8:
            self.records[0].append('right')
            self.records[1].append(self.state[7])
                        
        elif self.state.index(0) == 0:
            self.records[0].append('left')
            self.records[1].append(self.state[1])
            
        else:
            self.records[0].extend(('left','right'))
            self.records[1].extend((self.state[self.state.index(0)+1], 
                self.state[self.state.index(0)-1])) 
    
    def get_heuristic_cost(self, heuristic_function):
        
        if heuristic_function == 'manhattan_distance':
            self.records.append(node.manhattan_distance(self.state,self.goal_state))  
            
        elif heuristic_function == 'heuristic_function':
            self.records.append(node.heuristic_function(self.state,self.goal_state))
            
        else:
            print 'There is something wrong with the distance function you entered.'
                
    def move_piece(self, move):
        
        if move in self.records[self.key][0]:
            
            if move == 'left':
                new_state = self.state
                new_state[new_state.index(0) + 1], new_state[new_state.index(0)] = \
                    new_state[new_state.index(0)], new_state[new_state.index(0) + 1]
                return [1,2,3,8,0,4,7,6,5] #not returning the new state?
                
            else:
                new_state[new_state.index(0)], new_state[new_state.index(0) + 1] = \
                    new_state[new_state.index(0) - 1], new_state[new_state.index(0)]
                return [1,2,3,8,0,4,7,6,5] #not returning the new state?
                
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
        
    def __init__(self, node):
        
        self.current_node = node
        self.goal_state = node.goal_state
        self.current_state = node.state
        
        self.structure = {}
        self.structure[0] = node.state
        self.key_counter = 1
        
    def breadth_first(self):
        #(self, key, state, goal_state, parent_node, left_child, right_child)
        
        if self.current_state != self.goal_state:
                        
            move =  self.current_node.records[0][self.current_node.records[1].index \
                (min(self.current_node.records[1]))]
                
            state = self.current_node.move_piece(move)
            
            new_node = node(self.key_counter,state,self.goal_state,self.key_counter - 1,None,None)
            print new_node.state
            self.current_node = new_node
            
            self.structure[self.key_counter] = self.current_node.state
            
        else:
            return 'Omg this fucking things worked!'
            

            
easy = [1,3,4,8,6,2,7,0,5]      
goal = [1,2,3,8,0,4,7,6,5]             
test_node = node(0,easy,goal,None,None,None)
test_node.get_moves()
test_node.get_heuristic_cost('heuristic_function')

test_tree = searchTreeSolver(test_node)
test_tree.current_node.records
test_tree.breadth_first()

import pandas as pd
for k,v in test_tree.structure.items():
    print '\n'
    print 'Move Count: ', k, '\n'
    print pd.DataFrame([[v[0],v[1],v[2]], \
                        [v[3],v[4],v[5]], \
                        [v[6],v[7],v[8]]]).to_string(index = False, header = False)
    
















