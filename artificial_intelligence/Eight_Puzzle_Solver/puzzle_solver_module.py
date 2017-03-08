class node:

    @staticmethod
    def get_h_cost(state, goal, function):

        '''
        :param state: a list item representing the 8 puzzle state
        :param goal: a list item representing the goal of hte 8 puzzle state
        :param function: from the list ['manhattan_distance' , 'out_of_position', 'euclidean_distance' ~ default]
        :return: an integer representing the heuristic cost
        '''

        heuristic_cost = 0

        # the number of puzzle pieces out of place
        if function == 'out_of_position':
            for x in zip(state, goal):
                if x[0] != x[1]:
                    heuristic_cost += 1
                else:
                    continue

        # manhattan distance of the puzzle pieces
        # to their goal state
        elif function == 'manhattan_distance':
            for x in goal:
                heuristic_cost += abs(goal.index(x) - state.index(x))

        # euclidean distance of the puzzle pieces to their
        # goal state
        else:
            for x in goal:
                heuristic_cost += (goal.index(x) - state.index(x))**2

        return heuristic_cost

    def __init__(self, key, state, parent, g_of_n, depth, h_function, goal, move):

        '''
        a class to create nodes in a search tree

        :param key: an integer used to index/identify the node
        :param state: a list item representing the state of the node
        :param parent: the key of the parent node
        :param g_of_n: the cost of the move to this node, cumulative
        :param depth: the depth of the node
        :param h_function: the heuristic function to calculate heuristic cost
        :param goal: a list item, the goal state of the puzzle
        :param move: the move that was made to get to the state

        :attr key: see above ^
        :attr state: see above ^
        :attr parent: see above ^
        :attr g_of_n: ^
        :attr depth: ^
        :attr h_function: ^
        :attr goal: ^
        :attr h_of_n: the heuristic cost
        :attr total_cost: heuristic cost plus the cost
        :attr moves: a list of moves the current state can make
        :attr move: the move made to obtain the current state

        **get_moves method is run at initialization
        '''

        self.key = key
        self.state = state
        self.parent = parent
        self.g_of_n = g_of_n
        self.depth = depth
        self.h_function = h_function
        self.goal = goal
        self.move = move
        self.h_of_n = node.get_h_cost(self.state , self.goal , self.h_function)
        self.total_cost = self.g_of_n + self.h_of_n
        self.get_moves()

    def get_moves(self):

        '''
        a method to find the possible moves of a given state

        :return: a list of possible moves in the current state
        '''

        self.moves = []

        # possible moves were hard coded depending on the state
        if self.state.index(0) == 0:  self.moves.extend(('left','up'))
        elif self.state.index(0) == 1:  self.moves.extend(('left','right','up'))
        elif self.state.index(0) == 2:  self.moves.extend(('right','up'))
        elif self.state.index(0) == 3:  self.moves.extend(('up','down', 'left'))
        elif self.state.index(0) == 4:  self.moves.extend(('up','down','left','right',))
        elif self.state.index(0) == 5:  self.moves.extend(('right','up', 'down'))
        elif self.state.index(0) == 6:  self.moves.extend(('down','left'))
        elif self.state.index(0) == 7:  self.moves.extend(('left','right', 'down'))
        else:  self.moves.extend(('right','down'))
    
    def move_piece(self, move):

        '''
        a method is used to move the pieces to a new state

        :param move: from the list ['left','right','up','down']
        :return: list item representing new node state, an integer representing the cost
        '''

        new_node = self.state[:]
        zero_idx = new_node.index(0)

        if move == 'left':  rep_idx = zero_idx + 1
        elif move == 'right':  rep_idx = zero_idx - 1
        elif move == 'up':  rep_idx = zero_idx + 3
        else:  rep_idx = zero_idx - 3

        # rep_val also represents the cost
        # since it's the value of the piece being moves
        rep_val = self.state[rep_idx]
        new_node[zero_idx] = rep_val
        new_node[rep_idx] = 0
        return new_node , rep_val

class queue:

    def __init__(self, search_algorithm, goal_state):

        '''
        a class to create a queue for a search tree

        :param search_algorithm: supported search algorithms ['breadth_first','depth_first','uniform_cost','a_star','best_first']

        :attr search_algorithm: see above ^
        :attr queue: a list item to store items of class node, see above ^
        '''
        self.search_algorithm = search_algorithm
        self.queue = []

    def return_node(self):

        '''
        a method for returning a new node

        :return: a object of class node
        '''
        if self.search_algorithm == 'breadth_first':  return self.queue[0] # first in first out
        elif self.search_algorithm == 'depth_first':  return self.queue[-1] # last in first out
        elif self.search_algorithm == 'uniform_cost':  return sorted(self.queue, key=lambda x: x.g_of_n)[0]  # return node with the lowest cost
        elif self.search_algorithm == 'a_star':  return sorted(self.queue, key=lambda x: x.total_cost)[0] # return node with the lowest total cost
        elif self.search_algorithm == 'best_first':  return sorted(self.queue, key=lambda x: x.h_of_n)[0] # return node with the lowest heuristic cost
        elif self.search_algorithm == 'iterative_deepening': return sorted(self.queue, key=lambda x: x.depth)[0]

class searchTreeSolver:

    def __init__(self, node_init, goal_state, search_algorithm, iterative_deep):

        '''
        a class to iteratively create a search tree and solve the 8 puzzle

        :param node_init: an object of class node to be used as the root
        :param goal_state: a list item representing the goal state
        :param search_algorithm: the search algorithm of choice, supported ['breadth_first','depth_first','uniform_cost','a_star','best_first']

        :attr goal_state: a list item representing the goal state
        :attr current_node: a node object to initialize the tree
        :attr search_algorithm: the search algorithm used to solve the tree, see above ^
        :attr heuristic_function: the heuristic cost function, taken from the root node object
        :attr key: a key used to iteratively make nodes
        :attr move_counter: the move counter, only incremented when a new node is popped off that wasn't visited
        :attr tree: a dictionary storing the node objects in the tree
        :attr queue: a queue object used to store the nodes to return
        :attr visited_states: a list of states in list form that have been visited
        :attr depth_counter: used for iterative deepening, the depth of search tree is currently on
        :attr limit: used for iterative deepening, the depth limit the search tree can currently go to

        ** the solver method is run on initialization
        '''

        self.goal_state = node_init.goal
        self.current_node = node_init
        self.root = node_init
        self.search_algorithm = search_algorithm
        self.heuristic_function = node_init.h_function
        self.iterative_deep = iterative_deep
        self.key = 0
        self.move_counter = 0
        self.tree = {}
        self.queue = queue(self.search_algorithm, self.goal_state)
        self.queue.queue.append(self.root)
        self.visited_states = []
        self.depth_counter = 0
        self.limit = 0
        self.tree[0] = self.root
        self.solver()

    def solver(self):

        '''
        a method used to iteratively solve the 8 puzzle by creating a
        search tree
        '''

        import time
        start_time = time.time()
        self.current_node = self.queue.return_node()

        while self.queue:

            # used to find the max length of the queue at the end
            que_len = []
            que_len.append(len(self.queue.queue))

            # check for goal convergence
            if self.current_node.state != self.goal_state:

                # if iterative deepening has been set instead of having another outer for loop
                # we just reinitialize the tree and set increment the limit we can go to do this we use the limit and depth counters
                if self.iterative_deep:
                    if self.depth_counter > self.limit:
                        self.limit += 1
                        self.key = 0
                        self.move_counter = 0
                        self.tree = {}
                        self.queue = queue(self.search_algorithm, self.goal_state)
                        self.queue.queue.append(self.root)
                        self.visited_states = []
                        self.depth_counter = 0
                        self.current_node = self.root
                    else:  pass
                else:  pass

                # repeated state checking...so we aren't made fun of in class
                if self.current_node.state not in self.visited_states:
                    self.visited_states.append(self.current_node.state[:])
                    self.move_counter+=1

                    # for the current node, begin looping through possible nodes so we can make new nodes in the tree, we use the
                    # move piece method to iteratively return new states and the cost of the move
                    for move in self.current_node.moves:
                        self.key += 1
                        new_state , g_of_n = self.current_node.move_piece(move)
                        g_of_n += self.current_node.g_of_n
                        new_node = node(key=self.key,state=new_state,parent=self.current_node.key,g_of_n = g_of_n,depth=self.depth_counter+1,\
                                        h_function=self.heuristic_function,goal=self.goal_state,move=move)
                        self.tree[self.key] = new_node

                        # for searches that sort on cost, we have to search the queue to see if the states exists in the queue, if so
                        # we have to check and see if the cost of these existing nodes is more, if it isn't we leave the node in the queue
                        # if we use depth first or iterative search, we can skip this step since checking for repeated state will solve this problem
                        if self.search_algorithm in ['uniform_cost', 'a_star', 'best_first']:
                            c = 0
                            if self.search_algorithm == 'uniform_cost':  sort = 'g_of_n'
                            elif self.search_algorithm == 'a_star':  sort = 'total_cost'
                            else:  sort = 'h_of_n'

                            for i in self.queue.queue:
                                    if i.state == new_node.state:
                                        if getattr(i,sort) > getattr(new_node,sort):
                                            del self.queue.queue[c]
                                        else:  c+=1
                                    else:  c += 1

                        else:  pass

                        # even if our new node shares a state with a lower cost node
                        # we can simply add it knowing it will be sorted to the back
                        # of the queue or removed in current_state checking, while this
                        # is an odd solution to the overlap checking for cost based searches,
                        # it allows us to just add the new node for all our search algorithms
                        # simplified the code complexity
                        self.queue.queue.append(new_node)

                    # we add to the depth counter ,which is only
                    # used for iterative search and we return a new
                    # node and go back to the top of the while loop
                    self.depth_counter+=1
                    self.current_node = self.queue.return_node()

                else:
                    # if the search algorithm is depth first, we will delete from the back
                    # else we will delete from the front, the idx variable will define where
                    # we delete
                    if self.search_algorithm == 'depth_first':  idx = -1
                    else:  idx = 0

                    # if we need to sort the queue we do, so we delete the proper node from the queue in the case of uniform cost,
                    # we sort by cost, best first we sort by heuristic cost and a star we sort by total cost
                    if self.search_algorithm == 'uniform_cost':  self.queue.queue = sorted(self.queue.queue, key=lambda x: x.g_of_n)
                    elif self.search_algorithm == 'best_first':  self.queue.queue = sorted(self.queue.queue, key=lambda x: x.h_of_n)
                    elif self.search_algorithm == 'a_star':  self.queue.queue = sorted(self.queue.queue, key=lambda x: x.total_cost)
                    else:  pass

                    # we now delete an item from the queue based on the index defined above
                    del self.queue.queue[idx]
                    self.current_node = self.queue.return_node()

            else:
                # the puzzle has been solved so we can break the while loop and end the madness
                break

        # after we end the search, we begin printing results this loop will fine the goal state in the tree to set
        # our end node due to the way nodes were added to the tree, it's possible the goal node was added to the tree before it was found
        # this is because when a node is made, it's added to the tree but not necessarily popped off the queue
        end_time = time.time()
        for k,v in self.tree.items():
            if v.state == self.goal_state:
                kinit = k
                break
            else:  continue

        # this will loop through the tree and find the path until we reached the root node
        # items are added in reverse order so we can print the path
        path_list = [kinit]
        while kinit != 0:
            path_list.insert(0, self.tree[kinit].parent)
            kinit = path_list[0]

        for i in path_list:
            print 'Move:', self.tree[i].move, '\n', 'Heuristic Cost:', self.tree[i].h_of_n, '\n', 'Total Cost:',self.tree[i].g_of_n,\
                '\n', self.tree[i].state[0:3], '\n', self.tree[i].state[3:6], '\n', self.tree[ i].state[6:], '\n'
        print 'Total Moves Made: ', len(path_list) - 1 # don't include the initial state as a move
        print '8 Puzzle Solved', '\n', '~~Max Queue Length:', max(que_len), '\n', '~~Nodes Popped:', self.move_counter, '\n',\
            '~~Runtime:', end_time - start_time, '\n', '~~Total Moves:', self.move_counter, '\n'




goal = [1,2,3,8,0,4,7,6,5]
easy = [1,3,4,8,6,2,7,0,5]
medium = [2,8,1,0,4,3,7,6,5]
hard = [5,6,7,4,0,8,3,2,1]
tester = [1,0,3,8,2,4,7,6,5]
hfun = 'out_of_position'
salgo = 'a_star'
ideep = False
test_node = node(key=0,state=easy,parent=0,g_of_n=0,depth=0,h_function=hfun,goal=goal,move='Initial State')
test = searchTreeSolver(test_node,goal_state=goal,search_algorithm=salgo,iterative_deep=ideep)

