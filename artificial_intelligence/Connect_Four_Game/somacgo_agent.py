class gameBoard:

    @staticmethod
    def make_move(board,row,column,piece):

        '''
        :param board: a 9x9 numpy matrix
        :param row: the row to place the piece
        :param column: the column to place the piece
        :param piece: the game piece ['A','H']
        :return: a new board with the move made
        '''

        # a copy is created so the passed board variable
        # isn't overwritten when a move is made
        import copy
        new_board = copy.copy(board)
        new_board[row,column] = piece
        return new_board


    @staticmethod
    def get_score(board,piece):

        '''
        :param board: a 9x9 numpy array
        :param piece: the piece to get the score for ['A','H']
        :return: an int, score for the game player
        '''

        # each element of the playing board is looped through
        import numpy as np
        score = 0
        for (r, c), value in np.ndenumerate(board):

            # if the game board piece is for the player
            # the 8 neighbor pieces are found
            if value == piece:

                adj = [[r+1,c],[r-1,c],[r,c+1],[r,c-1],\
                       [r+1,c+1],[r+1,c-1],[r-1,c+1],[r-1,c-1]]

                for i in adj:

                    # in some cases, the index will be
                    # out of bounds, since it could be the last
                    # column, row etc. so exception handling is used
                    # the first 4 items in adj represent the pieces
                    # to the left, right, top and bottom, so we
                    # increment score by 2, otherwise we
                    # increment score by 1 for the diaganols

                    try:

                        if board[i[0], i[1]] == piece:

                            if adj.index(i) < 4:
                                score += 2
                            else:
                                score += 1
                        else:
                            pass

                    except:
                        continue

            else:
                continue

        return score/2


    @staticmethod
    def get_moves(board):

        '''
        :param board: a 9x9 numpy matrix
        :return: a list of moves to make, where each element is
         a list of type [row,column]
        '''

        import numpy as np
        column = 0
        moves = []

        # using the transpose, we can look at each column
        # and simply find the max of where a star is,
        # providing us the point a piece can drop to
        # the first check is to make sure the column
        # isn't full yet
        for i in board.T:

            if sum(np.char.count(i, '*')) == 0:
                column += 1
            else:
                moves.append([max(max(np.where(i == '*'))), column])
                column += 1

        return moves


    @staticmethod
    def alpha_beta_pruning(board,depth,alpha,beta,player,limit,move_cost):

        '''
        :param board: a 9x9 numpy array
        :param depth: the depth to test min max
        :param alpha: a value used to inform prunning for the max player
        :param beta: a value used to inform prunning for the min player
        :param player: the player the game will start with, always 'A'
        :param limit: the depth, used because the depth value is continually
         being adjusted in the recursion, this is kept static
        :param move_cost: a blank list that will store the moves and a cost
        :return: a nested list where the first item is move coordinates
         and the second item is the cost of that move, defined by
         the min max algorithm
        '''

        # if the depth is 0, this means we have hit the bottom of the min-max
        # algorithm and we begin retruning costs
        if depth == 0: 
            return gameBoard.get_score(board,'A') - gameBoard.get_score(board,'H')

        else:

            # since this player is maximizing, we find the minimum of the cost
            # of each node and the previous node cost, we then find the max of this
            # cost and alpha, if there is a case where beta is less then alpha
            # we can stop looking at this node
            if player == 'A':

                running_cost_alpha = -100000
                moves = gameBoard.get_moves(board)

                for move in moves:

                    new_board = gameBoard.make_move(board,move[0],move[1],'A')
                    running_cost_alpha = max(running_cost_alpha,gameBoard.alpha_beta_pruning(new_board,depth-1,alpha,beta,'H',limit, move_cost))
                    alpha = max(alpha,running_cost_alpha)

                    if beta <= alpha: return alpha
                    else: pass

                    if depth == limit: move_cost.append([move,alpha])
                    else: pass

                return alpha

            else:

                # since this player is minimizing, we create a large value v
                # that is overwritten by finding the min of v and the cost, returned
                # from alpha beta prunning, beta is used to test the prunning, if beta
                # is less than alpha, we don't need to continue testing the children of this node
                running_cost_beta = 100000
                moves = gameBoard.get_moves(board)

                for move in moves:

                    new_board = gameBoard.make_move(board, move[0], move[1], 'H')
                    running_cost_beta = min(running_cost_beta,gameBoard.alpha_beta_pruning(new_board,depth-1,alpha,beta,'A',limit,move_cost))
                    beta = min(beta,running_cost_beta)

                    if beta <= alpha: return beta
                    else: pass

                return beta

    def __init__(self,depth):

        '''
        :param depth: the depth alpha beta prunning will be applied
        :attr board: a 9x9 numpy array storing the game state
        :attr mover: the person who's turn it is ['H','A']
        :attr cost_move: this is used to store the costs of a move
         for the AI when alpha_beta pruning is applied
        :attr depth: the depth to apply alpha_beta pruning
        :attr limit: the depth to apply alpha_beta pruning, this
         is used in the alpha_beta function as depth get's
         overwritten in the recursion
         this is a class that will initialize a 9x9 gameboard to play
         to samcogo.  what a tremendous game it is
        '''

        import numpy as np
        self.board = np.chararray((9,9))
        self.board[:] = '*'
        self.mover = 'H'
        self.cost_move = []
        self.depth = depth
        self.limit = depth


    def play_game(self):

        import numpy as np
        game_bool = True

        while game_bool:

            print 'Human:',gameBoard.get_score(self.board,'H'), '\n', 'AI:',gameBoard.get_score(self.board,'A'), '\n'

            # if there is no moves left, we end the game
            if sum(sum(np.char.count(self.board, '*'))) == 0: game_bool = False
            else: pass

            if self.mover == 'H':

                # gather the possible moves for the human and
                # prompt the user to end the row and column
                # of where they'd like to place a piece
                possible_moves = gameBoard.get_moves(self.board)
                print self.board, '\n','Possible Moves: [Row,Column]','\n',possible_moves,'\n'
                row = eval(raw_input('Enter a row: '))
                column = eval(raw_input("Enter a column: "))

                # using the entered move, replace the tile with an
                # H tile and switch the mover to the AI agent
                self.board[row,column] = 'H'
                self.mover = 'A'

            else:

                # alpha beta pruning is applied, where we pass in the board, depth, some extreme
                # values to initialize alpha and beta at and the cost_move list which will store
                # the persisted costs
                gameBoard.alpha_beta_pruning(self.board,self.depth,-10000,10000,'A',self.limit,self.cost_move)

                # the max will be used to find the lowest cost move
                max = -1000
                move = None

                # looking at the possible moves and costs
                # we find the maximum cost move and proceed
                # to make the move, the shuffle is to help randomize
                # ties in the cost
                import numpy as np
                np.random.shuffle(self.cost_move)
                for i in self.cost_move:

                    if i[1] > max: move = i[0]
                    else: continue

                self.board[move[0], move[1]] = 'A'
                self.cost_move = []
                self.mover = 'H'

        # after the board is filled up, we print out the final board and score
        print 'Final Score', '\n','Human:',gameBoard.get_score(self.board,'H'),'\n','AI Agent:', gameBoard.get_score(self.board,'A')


# the game can be played using the below, where only the depth
# has to be defined and the play_game method called
depth = 5
test = gameBoard(depth)
test.play_game()
