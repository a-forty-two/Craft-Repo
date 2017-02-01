class gameBoard:

    # returns a new board state
    @staticmethod
    def make_move(board,row,column,piece):
        board[row,column] = piece
        return board

    # returns an integer
    @staticmethod
    def get_score(board,piece):
        score = 0
        # get scoring left and right
        for row in board:
            for item in range(0, 9):
                if row[item] == piece:
                    try:
                        if row[item + 1] == piece: score += 2
                    except:  continue

        # get scoring top and bottom
        for row in board.T:
            for item in range(0, 9):
                if row[item] == piece:
                    try:
                        if row[item + 1] == piece: score += 2
                    except:  continue

        # get scoring diaganol
        r_counter = 0
        for row in board:
            if r_counter in [1, 3, 5, 7]:
                for item in range(0, 9):
                    if row[item] == piece:
                        if board[r_counter - 1, item + 1] == piece:  score += 1
                        elif board[r_counter - 1, item - 1] == piece:  score += 1
                        elif board[r_counter + 1, item + 1] == piece:  score += 1
                        elif board[r_counter + 1, item - 1] == piece:  score += 1
                        else:  pass
                    else:  pass
            else:  r_counter += 1
        return score

    # returns nested list of moves | [[0,8],[1,8],[2,8],[3,8]...]
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
        print moves

    # initializes a 9x9 game board
    # self.board
    # self.mover
    def __init__(self):
        import numpy as np
        self.board = np.chararray((9,9))
        self.board[:] = '*'
        self.mover = 'Human'

    def alpha_beta_pruning(board,depth,alpha,beta,max_player,moves):

        print depth
        if depth == 0:
            print board.get_score(board.board,'A') - test_board.get_score(board.board,'H')
        else:
            pass

        if max_player: # this means we need to maximize here

            v = -10000000000000000 # some high value in place of inf

            for i in moves:

                # get the new state, or node using the move and
                board_get = test_board.make_move(i[1], i[0], board)
                # this is going to run alpha beta for the new board
                v = max(v, alpha_beta_pruning(board_get, (depth - 1) , alpha, beta , False, moves))
                alpha = max(alpha, v) # some really small value or the cost of the move

                if beta <= alpha:
                    break
                else:
                    continue
            return v

        else:

            v = 10000000000

            for i in moves:
                board_get = test_board.make_move(i[1], i[0], alpha)
                v = max(v, alpha_beta_pruning(board_get, (depth - 1) , alpha, beta , True, moves))
                beta = min(beta, v)

                if beta <= alpha:
                    break
                else:
                    continue

            return v

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

                # alpha_beta_pruning(board,depth,alpha,beta,max_player,moves)
                alpha_beta_pruning(self.board, depth , -10000000000000 , 1000000000000 , True , possible_moves)

                    # this means we are at the top or we have no more moves to make
                    if depth == 0 or sum(map(lambda x: sum(x), possible_moves)) == 0:
                        print board.get_score(board.board,'A') - test_board.get_score(board.board,'H')
                    else:
                        pass

                    # this means this player is maximizing
                    if max_player:

                        v = -10000000000000000 # some high value in place of inf

                        for i in possible_moves:

                            # get the new state using the move
                            board_get = gameBoard.make_move(i[1], i[0], board)
                            v = max(v, alpha_beta_pruning(board_get, (depth - 1) , alpha, beta , False, moves))
                            alpha = max(alpha, v) # some really small value or the cost of the move

                            if beta <= alpha:
                                break
                            else:
                                continue
                        return v

                    else:
                        v = 10000000000

                        for i in moves:
                            board_get = test_board.make_move(i[1], i[0], alpha)
                            v = max(v, alpha_beta_pruning(board_get, (depth - 1) , alpha, beta , True, moves))
                            beta = min(beta, v)

                            if beta <= alpha:
                                break
                            else:
                                continue

                        return v

                # update the board
                self.board[row, column] = 'A'
                self.mover = 'Human'

lst = [[],[]]
print sum(map(lambda x: sum(x), lst))
