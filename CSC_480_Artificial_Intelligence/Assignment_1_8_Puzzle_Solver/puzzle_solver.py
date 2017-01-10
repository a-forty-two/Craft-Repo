class node:

    '''
    -----------------------------------------------------------------------------------------
    !                                                                                       !    
    ! node class                                                                            !
    !                                                                                       !
    ! the node class is initialized with the following parameters:                          !
    !                                                                                       !
    !   ~ key = a key to identify the node when building a search tree                      !
    !   ~ state = a list that represents the order of the puzzle                            !
    !   ~ parent_node = the key of the parent node, if applicable, initialized to null      !
    !   ~ left_child = the left child of the node, if applicable, initialized to null       !
    !   ~ right_child = the right child of the node, if applicable, initialized to null     !
    !                                                                                       !
    !    upon initialization, the get_moves method is run to store the types                ! 
    !    of moves that can be made in the current state                                     !
    !                                                                                       !
    !                                                                                       !
    -----------------------------------------------------------------------------------------

    '''

    def __init__(self, key, state, parent_node, left_child, right_child):
        
        '''
        an initialization method to create a node object
        '''
        
        self.key = key
        self.state = state
        self.parent_node = None
        self.left_child = None
        self.right_child = None
        self.get_moves()        
        
    def get_moves(self):
        
        '''
        the get moves method will return an attribute of type nested list
        the first item in the list is the possible moves and the second item
        is a blank list to store the costs of moving the pieces left or right
        this will be used to search the tree and find the best path
        '''
        
        self.moves = [[],[]]
        
        if self.state.index(0) == 8: # if the 0 is at the end, only the right piece can move
            self.moves[0].append('right')
            
        elif self.state.index(0) == 0: # if the 0 is at the beginning a piece can only move left
            self.moves[0].append('left')
            
        else: # otherwise, there can be both left and right moves
            self.moves[0].extend(('left','right'))
            
            
    def move_piece(self, move):
        
        '''
        the move piece method will take a move ['left','right'], and
        return the new state after the move has been made.
        '''
                
        new_node = self.state[:] # the [:] creates a new copy, while eliminating the pointer reference
        
        if move in self.moves:
            
            if move == 'left': # if the move is left, we move the piece to the right of the 0 left
                zero_rep = new_node[new_node.index(0) + 1]
                zero_idx = new_node.index(0)
                rep_idx = new_node.index(0) + 1
                new_node[rep_idx] = 0
                new_node[zero_idx] = zero_rep
                return new_node
    
            else: # if the move is right, we move the piece to the left of the 0 left
                zero_rep = new_node[new_node.index(0) - 1]
                zero_idx = new_node.index(0)
                rep_idx = new_node.index(0) - 1
                new_node[rep_idx] = 0
                new_node[zero_idx] = zero_rep
                return new_node
                
        else:
            return 'In the given state a {enter_move} is not possible.'.format(enter_move = move)
             

class searchTreeSolver:
    
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
    def get_heuristic_cost(cost_function, state, goal):
        
        if cost_function == 'manhattan_distance':
            return searchTreeSolver.manhattan_distance(state,goal) 
            
        elif cost_function == 'heuristic_function':
            return searchTreeSolver.heuristic_function(state,goal)
            
        else:
            print 'There is something wrong with the distance function you entered.'
        
    @staticmethod 
    def get_a_star_cost(h_cost, q_cost):
        return h_cost + q_cost
        
        
    def __init__(self, node_init, goal_state, cost_function):
        
        self.root = node_init.state
        self.current_node = node_init
        self.structure = {} # keep track of the nodes
        self.structure[0] = node_init
        self.key_counter = 1
        self.goal_state = goal_state
        self.cost_function = cost_function
        self._queue_ = queue()
        self._queue_.add(0) 
        
    def breadth_first(self):  
    
        for i in self.structure[self._queue_.head()].moves[0]:
            return self.structure[self._queue_.head()].move_piece(i)
            

        
            
            
            
        
        

easy = [1,3,4,8,6,2,7,0,5]      
goal = [1,2,3,8,0,4,7,6,5] 

test_node = node(0,easy,None,None,None)
test_tree = searchTreeSolver(test_node, goal, 'manhattan_distance')
test_tree.breadth_first()

import pandas as pd
for k,v in test_tree.structure.items():
    print v.moves

    
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

queue = queue()
queue.add(5)
queue.add(10)
queue._queue
queue.head()
queue.remove_head()
queue._queue





