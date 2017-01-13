class node:

    def __init__(self, key, state, parent, parent_cost):

        self.key = key
        self.state = state
        self.parent_node = parent
        self.parent_cost = parent_cost
        self.left_child = None
        self.right_child = None
        self.get_moves()
        
    def get_moves(self):
                
        self.moves = [[],[],[]] # move, cost, heuristic_cost
        if self.state.index(0) == 0: self.moves[0].extend(('left','up'))
        elif self.state.index(0) == 1: self.moves[0].extend(('left','right','up'))
        elif self.state.index(0) == 2: self.moves[0].extend(('right','up'))
        elif self.state.index(0) == 3: self.moves[0].extend(('up','down', 'left'))
        elif self.state.index(0) == 4: self.moves[0].extend(('left','right','up','down'))
        elif self.state.index(0) == 5: self.moves[0].extend(('right','up', 'down'))
        elif self.state.index(0) == 6: self.moves[0].extend(('down','left'))
        elif self.state.index(0) == 7: self.moves[0].extend(('left','right', 'down'))
        else: self.moves[0].extend(('right','down'))
    
    def move_piece(self, move):

        new_node = self.state[:]
        zero_idx = new_node.index(0)
        if move == 'left': rep_idx = zero_idx + 1
        elif move == 'right': rep_idx = zero_idx - 1
        elif move == 'up': rep_idx = zero_idx + 3
        else: rep_idx = zero_idx - 3 # move down
               
        # begin altering the puzzle to the new state
        rep_val = self.state[rep_idx]
        new_node[zero_idx] = rep_val
        new_node[rep_idx] = 0
        return new_node , rep_val

class queue:

    def __init__(self, search_algorithm):

        self.search_algorithm = search_algorithm
        self.queue = []

    def return_node(self):

        if self.search_algorithm == 'breadth_first':
            return self.queue[0][0] # pop off the front
        elif self.search_algorithm == 'depth_first':
            return self.queue[-1][0] # pop off the back
        elif self.search_algorithm == 'uniform_cost':
            return sorted(self.queue,key=lambda x: x[1])[0][0]

            #queue = [node,cost]
        elif self.search_algorithm == 'a_star':
            pass
        else:
            pass

    @staticmethod
    def heuristic_cost(state, goal, function):

        heuristic_cost = 0
        if function == 'out_of_position':
            for x in zip(state, goal):
                if x[0] != x[1]: heuristic_cost += 1
                else: continue

        elif function == 'manhattan_distance':
            for x in goal: heuristic_cost += abs(goal.index(x) - state.index(x))

        else: # 'euclidean distance'
            pass

        return heuristic_cost

    @staticmethod
    def get_cost(state,goal):
        cost = 0
        for i in range(0, len(state)):
            if state[i] != goal[i]: cost += 1
            else: continue
        return cost

    @staticmethod
    def get_a_star_cost(heuristic_cost, cost):
        return heuristic_cost + cost


class searchTreeSolver:


    def __init__(self, node_init, goal_state):

        self.goal_state = goal_state
        self.tree = {} # this will store our actual tree
        self.tree[0] = [node_init,0] # create the root, cost
        self.key = 0 # use this to create the tree structure
        self.visited_states = []

    def solver(self, search_algorithm):

        # initialize our queue
        self.search_algorithm = search_algorithm
        self.move_counter = 0
        self.queue = queue(self.search_algorithm)  # make our queue
        self.queue.queue.append([self.tree[0][0],0])        # initially pop an item off the queue

        #pop an item on the queue
        self.current_node = self.queue.return_node()

        while self.queue:

            # check for goal state
            if self.current_node.state != self.goal_state:

                #make sure it wasn't visited
                if self.current_node.state not in self.visited_states:

                    # print out the current node and reset the state

                    print self.move_counter
                    self.move_counter += 1
                    print self.current_node.state[0],self.current_node.state[1],self.current_node.state[2]
                    print self.current_node.state[3],self.current_node.state[4],self.current_node.state[5]
                    print self.current_node.state[6],self.current_node.state[7],self.current_node.state[8]
                    print '\n'


                    # add as visited
                    self.visited_states.append(self.current_node.state)

                    # get moves and states
                    for move in self.current_node.moves[0]:
                        new_state,cost = self.current_node.move_piece(move)
                        new_node = node(key = self.key , state=new_state , parent=self.current_node.key, parent_cost=cost)
                        cost += self.current_node.parent_cost
                        self.queue.queue.append([new_node,cost]) # add to the queue
                        self.key += 1
                    self.current_node = self.queue.return_node()

                else:
                    # it's been visited so we delete it from the queue and pop a new node
                    if self.search_algorithm == 'breadth_first' or self.search_algorithm == 'uniform_cost':
                        del self.queue.queue[0] # delete from the front of queue
                        print 'item was deleted', self.current_node.state
                        self.current_node = self.queue.return_node()
                    elif self.search_algorithm == 'depth_first':
                        del self.queue.queue[-1] # delete from the end of the queue
                        self.current_node = self.queue.return_node()
                        print 'item was deleted', self.current_node.state
                    else:
                        pass

            else:

                print self.move_counter
                print self.current_node.state[0], self.current_node.state[1], self.current_node.state[2]
                print self.current_node.state[3], self.current_node.state[4], self.current_node.state[5]
                print self.current_node.state[6], self.current_node.state[7], self.current_node.state[8]
                break


        print 'solved'



goal = [1,2,3,8,0,4,7,6,5]
easy = [1,3,4,8,6,2,7,0,5]
test_node = node(0,easy,'root',0)
test = searchTreeSolver(test_node,goal)
test.solver('uniform_cost')


