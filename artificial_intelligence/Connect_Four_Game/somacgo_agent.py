class gameBoard:

    @staticmethod
    def make_move(board,row,column,piece):
        import copy
        new_board = copy.copy(board)
        new_board[row,column] = piece
        return new_board

    @staticmethod
    def get_score(board,piece):
        score = 0
        # get scoring left and right
        for row in board:
            for item in range(0, 8):
                if row[item] == piece:
                    try:
                        if row[item + 1] == piece: score += 2
                    except:  continue

        # get scoring top and bottom
        for row in board.T:
            for item in range(0, 8):
                if row[item] == piece:
                    try:
                        if row[item + 1] == piece: score += 2
                    except:  continue

        # get scoring diaganol
        r_counter = 0
        for row in board:
            if r_counter in [1, 3, 5, 7]:
                for item in range(0, 8):
                    if row[item] == piece:
                        if board[r_counter - 1, item + 1] == piece:  score += 1
                        elif board[r_counter - 1, item - 1] == piece:  score += 1
                        elif board[r_counter + 1, item + 1] == piece:  score += 1
                        elif board[r_counter + 1, item - 1] == piece:  score += 1
                        else:  pass
                    else:  pass
            else:  r_counter += 1
        return score

    @staticmethod
    def get_moves(board):
        c_counter = 0
        moves = [[],[],[],[],[],[],[],[],[]]
        for column in board.T:
            r_counter = 0
            for row_item in column:
                if row_item == '*':
                    moves[c_counter] = [c_counter,r_counter]
                    r_counter  += 1
                else:
                    r_counter += 1
            c_counter += 1
        for move in moves:
            if len(move) != 2:
                del moves[moves.index(move)]
            else:
                pass
        return moves

    @staticmethod
    def alpha_beta_pruning(board,depth,alpha,beta,player):
        print '\n'
        print board
        print '\n'

        if depth == 0:
            return gameBoard.get_score(board,'A') - gameBoard.get_score(board,'H')

        else:

            if player == 'H':

                v = -10000000
                moves = gameBoard.get_moves(board)

                for move in moves:
                    new_board = gameBoard.make_move(board,move[1],move[0],'H')
                    cost = gameBoard.alpha_beta_pruning(new_board,depth-1,alpha,beta,'A')
                    v = max(v,cost)
                    alpha = max(alpha,v)

                    if beta <= alpha:
                        break
                    else:
                        continue

            else:

                v = 10000000
                moves = gameBoard.get_moves(board)

                for move in moves:

                    new_board = gameBoard.make_move(board, move[1], move[0], 'A')
                    cost = gameBoard.alpha_beta_pruning(new_board, depth - 1, alpha, beta, 'H')
                    v = min(v,cost)
                    beta = min(beta, v)

                    if beta <= alpha:
                        break
                    else:
                        continue

            return v

    def __init__(self):
        import numpy as np
        self.board = np.chararray((9,9))
        self.board[:] = '*'
        self.mover = 'Human'




test = gameBoard()
tester = test.board[:]
gameBoard.alpha_beta_pruning(tester,2,-100000000,10000000,'H')







    '''
    def play_game(self, depth):

        # this will be used to tell when to end the game
        game_bool = True

        while game_bool:

            # check to make sure there is open spaces
            if sum(sum(np.char.count(self.board, '*'))) == 0:
                game_bool = False
            else:
                continue

            # show the board
            print self.board

            if self.mover == 'Human':

                # get the list of moves for our user
                possible_moves = gameBoard.get_moves(self.board)
                print possible_moves, '\n'

                # user enters the moves
                column = eval(raw_input("Enter a column: "))
                row = eval(raw_input('Enter a row: '))

                # update board and change player
                self.board[row, column] = 'H'
                self.mover = 'AI'




            else: # AI agent

                # get the possible moves
                possible_moves = gameBoard.get_moves(self.board)



                # update the board
                self.board[row, column] = 'A'
                self.mover = 'Human'

'''