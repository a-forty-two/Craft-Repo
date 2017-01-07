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
    
    
    def __init__(self, key, state, goal_state, parent_node, left_child, right_child):
        
        self.key = key
        self.state = state
        self.parent_node = parent_node
        self.left_child = left_child
        self.right_child = right_child
        self.records = {}
        self.goal_state = goal_state
        
    def get_moves(self):
        
        self.records[self.key] = [[],[]]
        
        if self.state.index(0) == 8:
            self.records[self.key][0].append('right')
            self.records[self.key][1].append(self.state[7])
                        
        elif self.state.index(0) == 0:
            self.records[self.key][0].append('left')
            self.records[self.key][1].append(self.state[1])
            
        else:
            self.records[self.key][0].extend(('left','right'))
            self.records[self.key][1].extend((self.state[self.state.index(0)+1], 
                self.state[self.state.index(0)-1])) 
    
    def get_heuristic_cost(self, heuristic_function):
        
        if heuristic_function == 'manhattan_distance':
            self.records[self.key].append(node.manhattan_distance(self.state,self.goal_state))  
            
        elif heuristic_function == 'heuristic_function':
            self.records[self.key].append(node.heuristic_function(self.state,self.goal_state))
            
        else:
            print 'There is something wrong with the distance function you entered.'
                
    def move_piece(self, move):
        
        if move in self.records[self.key][0]:
            
            if move == 'left':               
                self.state[self.state.index(0) + 1], self.state[self.state.index(0)] = \
                    self.state[self.state.index(0)], self.state[self.state.index(0) + 1]
                self.update_records()
                return self.state
                
            else:
                self.state[self.state.index(0)], self.state[self.state.index(0) + 1] = \
                    self.state[self.state.index(0) - 1], self.state[self.state.index(0)]
                self.update_records()
                return self.state
                
        else:
            return 'In the given state a {enter_move} is not possible.'.format(enter_move = move)
        
        
        
easy = [1,3,4,8,6,2,7,0,5]      
goal = [1,2,3,8,0,4,7,6,5]


test_node = node(1, easy, goal, None, None, None)
test_node.get_moves()
test_node.get_heuristic_cost('manhattan_distance')
test_node.records















