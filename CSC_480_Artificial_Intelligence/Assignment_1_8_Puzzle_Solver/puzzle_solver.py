class node:

    def __init__(self, key, state):
        
        '''                                                                                           
        node class                                                                            
                                                                                               
        the node class is initialized with the following parameters:                          
                                                                                               
        ~ key = a key to identify the node when building a search tree                      
        ~ state = a list that represents the order of the puzzle                            
        ~ parent_node = the key of the parent node, if applicable, initialized to null      
        ~ left_child = the left child of the node, if applicable, initialized to null       
        ~ right_child = the right child of the node, if applicable, initialized to null     
                                                                                               
        upon initialization, the get_moves method is run to store the types                 
        of moves that can be made in the current state                                                                                                                                                                                                              !
        '''
                
        self.key = key
        self.state = state
        self.parent_node = None
        self.left_child = None
        self.right_child = None
        self.get_moves()        
        
    def get_moves(self):
        
        '''
        the get moves method get's the moves of the puzzle.  this method
        is run at the initalization of the node data structure.  if the 0
        initial state has a 0 in the first position, the 0 can move down or 
        right.  if the 0 is in the last position, the 0 can move left or up.
        
                                    ---------
                                    | * * * |
                                    | * x * |
                                    | * * * |
                                    ---------
                                    
        the moves attribute will be a nested list, which will be used to store the 
        moves and the cost of the move.  fo instance, if the 0, or blank space
        is in the center, the 0 can move all four directions.  no two states
        have the same moves.  therefore, we will look at each state and find
        the possible moves.  the cost will be calculated by the search tree 
        class and initialized to a blank list to store the costs. 
        '''
                
        self.moves = [[],[]]
        
        if self.state.index(0) == 0:
            self.moves[0].extend(('right','down'))  
            
        elif self.state.index(0) == 1:
            self.moves[0].extend(('left','right','down'))
            
        elif self.state.index(0) == 2:
            self.moves[0].extend(('left','down'))
            
        elif self.state.index(0) == 3:
            self.moves[0].extend(('up','down', 'right'))
            
        elif self.state.index(0) == 4:
            self.moves[0].extend(('left','right','up','down'))
            
        elif self.state.index(0) == 5:
            self.moves[0].extend(('left','up', 'down'))
            
        elif self.state.index(0) == 6:
            self.moves[0].extend(('up','right'))
            
        elif self.state.index(0) == 7:
            self.moves[0].extend(('left','right', 'up'))
            
        else:
            self.moves[0].extend(('left','up'))
    
    def move_piece(self, move):
        
        '''
        the move piece method will take the state of a node and the move
        desired, and first make sure the move is legal, then proceed
        to make the move.  a copy of the state is made and then adjusted
        according to the move and returned as a new state.  this allows
        a tree to take a state, make a move, and create a new node
        using said state.
        
        goal state

                                    ---------
                                    | 1 2 3 |
                                    | 8 0 4 |
                                    | 6 7 8 |
                                    ---------     

        indices of the list aligned to matrix
        
                                    ---------
                                    | 0 1 2 |
                                    | 3 4 5 |
                                    | 6 7 8 |
                                    ---------
                                    
        using the above, we see that the 8 piece will need to be in the list
        at index 3.  we can also use this to calculate the change in indicies
        among up and down movements.
        '''
        
        if move in self.moves[0]:
            
            new_node = self.state[:] # we use this syntax so new_node
            # is pointing to a different memory allocation then self.state
            zero_idx = new_node.index(0)
            
            # depending on how to move, we need to define which index
            # to switch the zero with. we can use the above examples
            # if the mvoe is left, a piece is moving left
            if move == 'left':
                rep_idx = zero_idx - 1
        
            elif move == 'right':
                rep_idx = zero_idx + 1
        
            elif move == 'up':
                rep_idx = zero_idx - 3
                    
            else: # move == 'down'
                rep_idx = zero_idx + 3
               
            # once we know the value to switch with the zero, we can
            # begin altering the puzzle to the new state
            rep_val = self.state[rep_idx]
            new_node[zero_idx] = rep_val
            new_node[rep_idx] = 0
            return new_node, rep_val
            
        else:
            return 'this move is not possible'
        
             

class searchTreeSolver:
    
     
    
    '''
    to figure out how much the heuristic cost is,
    we zip the values of the current state and goal state
    if they do not equal we increment the counter to see
    how many of the pieces are out of place.
    '''
    
    @staticmethod
    def heuristic_function(state, goal):
        h_cost = 0
        for x in zip(state, goal):
            if x[0] != x[1]:
                h_cost += 1
            else:
                continue
        return h_cost
    
    
    '''
    to calcualte the manhattan distance, we just need to 
    find the absolute value of stateN - goalN summation.  
    in this case, for each item in the goal state list,
    we find the index of where it is in the goal and the state
    and take the absolute value.  this returns the distance
    between the value for all pieces.
    '''
    
    @staticmethod 
    def manhattan_distance(state, goal):    
        h_cost = 0
        for x in goal:
            h_cost += abs(goal.index(x) - state.index(x))
        return h_cost
        
    @staticmethod
    def heuristic_function_self_def():
        pass


    '''
    using the above, we calculate the above cost for each move
    '''
    
    @staticmethod
    def get_heuristic_cost(cost_function, state, goal):
        
        if cost_function == 'manhattan_distance':
            return searchTreeSolver.manhattan_distance(state,goal) 
            
        elif cost_function == 'heuristic_function':
            return searchTreeSolver.heuristic_function(state,goal)
            
        else:
            print 'There is something wrong with the distance function you entered.'
        
    '''
    this method is used to return the cost for a star
    search.
    '''
    
    @staticmethod 
    def get_a_star_cost(h_cost, q_cost):
        return h_cost + q_cost
        
        
    def __init__(self, node_init, goal_state, cost_function):
        
        '''                                                                                           
        searchTreeSolver class                                                                            
                                                                                               
        the searchTreeSolver class is initialized with the following parameters:                          
                                                                                               
        ~ node_init = a node object of class node
        ~ goal_state = a list of the goal state
        ~ cost_function = from the array ('manhattan_distance', 'heuristic_function')
        returns the cost of the move
                                                                                                                                                                                                                                                                                                          !
        '''
        
        self.root = node_init.state
        self.current_node = node_init
        self.structure = {} 
        self.structure[0] = node_init
        self.key_counter = 1
        self.goal_state = goal_state
        self.cost_function = cost_function
        
    def get_move_cost(self):  
    
        '''                                                                                           
        get_move_cost 
                                                                                                                                                                                                                                                                                                          !
        '''
        
        for move in self.current_node.moves[0]:
            state = self.current_node.move_piece(move)
            cost = searchTreeSolver.get_heuristic_cost(self.cost_function, state, self.goal_state)
            self.current_node.moves[1].append(cost)
    
    def breadth_first_search_solver(self):
        
        queue = [0] # we start at the root
        visited = [0] # we considered the root visits
        self.get_move_cost()        
        
        while queue:
        
            if self.current_node.state != self.goal_state:

                # nested array of type (move,cost) combinations sorted by cost
                # therefore we can loop through all moves of a node in order of cost
                for move in sorted(zip(self.current_node.moves[1], self.current_node.moves[0]), key = lambda x:x[1]):
                    
                    # grab the move which is from the array ('left','right','up','down')
                    move_make = move[1]
                    
                    # we being to loop through and find the move with the lowest cost. 
                    new_state, cost = self.current_node.move_piece(move_make) #make that move
                    new_node = node(self.key_counter,new_state) # make a new node with that state
                    queue.append(self.key_counter) # add this new node to queue
                    visited.append(self.key_counter) #mark this as visited
                    self.structure[self.key_counter] = new_node # add this to the structure of our tree
                    self.key_counter += 1 # add to our key counter
                    
                    # print out the movement
                    print 'Move Number: ', self.key_counter
                    print 'The zero slot moves {move} at a cost of {cost}.'.format(move = move_make,cost=cost)
                    print ' --- --- --- ','     ',' --- --- --- '
                    print '|',self.current_node.state[0],'|',self.current_node.state[1],'|',self.current_node.state[2],'|',' >>> ','|', new_state[0],'|',new_state[1],'|',new_state[2],'|'                 
                    print '|--- --- ---|','     ',' --- --- --- '                                    
                    print '|',self.current_node.state[3],'|',self.current_node.state[4],'|',self.current_node.state[5],'|',' >>> ','|', new_state[3],'|',new_state[4],'|',new_state[5],'|'                 
                    print '|--- --- ---|','     ',' --- --- --- '                               
                    print '|',self.current_node.state[6],'|',self.current_node.state[7],'|',self.current_node.state[8],'|',' >>> ','|', new_state[6],'|',new_state[7],'|',new_state[8],'|' 
                    print ' --- --- --- ','     ',' --- --- --- '                    
                    print '\n'
                    
                    # test if we have reached the goal state
                    if new_state == self.goal_state:
                        break
                       
                # after searching the node, we must reset to the next in the queue
                # by removing the top item in the queue.
                del queue[0]
                self.current_node = self.structure[queue[0]]
                self.get_move_cost()
            
            else:
                print 'The goal state has been achieved'
                print ' --- --- --- ','     ',' --- --- --- '
                print '|',new_state[0],'|',new_state[1],'|',new_state[2],'|',' >>> ','|', self.current_node.state[0],'|',self.current_node.state[1],'|',self.current_node.state[2],'|'                 
                print '|--- --- ---|','     ',' --- --- --- '                
                print '|',new_state[3],'|',new_state[4],'|',new_state[5],'|',' >>> ','|', self.current_node.state[3],'|',self.current_node.state[4],'|',self.current_node.state[5],'|'                 
                print '|--- --- ---|','     ',' --- --- --- '                
                print '|',new_state[6],'|',new_state[7],'|',new_state[8],'|',' >>> ','|', self.current_node.state[6],'|',self.current_node.state[7],'|',self.current_node.state[8],'|' 
                print ' --- --- --- ','     ',' --- --- --- '
                break
        
easy = [1,3,4,8,6,2,7,0,5]      
goal = [1,2,3,8,0,4,7,6,5] 
test_node = node(0,easy)
test_tree = searchTreeSolver(test_node, goal, 'heuristic_function')
test_tree.breadth_first_search_solver()


