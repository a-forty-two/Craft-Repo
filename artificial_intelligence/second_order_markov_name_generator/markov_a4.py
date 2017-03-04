from random import randint

def load_names(gender):

    '''
    :param gender: the letter m or f (default is f)
    :return: a list of names
    '''

    boy_names = []
    girl_names = []

    appender = '__'

    b_raw = open('namesBoys.txt', 'r')
    g_raw = open('namesGirls.txt','r'
                 )
    for i in b_raw:
        i = i.replace('\r','').replace('\n','')
        new_name = appender + i + appender
        boy_names.append(new_name)

    for i in g_raw:
        i = i.replace('\r','').replace('\n','')
        new_name = appender + i + appender
        girl_names.append(new_name)

    if gender == 'm': return boy_names
    else: return girl_names

def get_transition_probabilities(current_state, lst):

    '''
    :param current_state: string of the last 2 letters of the name
    :param lst: a list of names
    :return: a list of next states based on the list of names input
    a list of type [a,a,a,a,b] means a follows the current state
    4 times and b follows once.
    '''

    transitions = []

    # for each name
    for i in lst:

        # convert the name to a list of letters
        letters = list(i)

        # loop through the letters
        for li in range(0,len(letters)):

            # we try to get the cocatination of the two letters
            # if the previous letter and current letter equal our
            # current state, then we add the next letter to
            # our transitions, for instance our state is 'br'
            # and our list is [b,r,i], once we get b then we get
            # can match br and append i, we use try and except to handle
            # when we are out of bounds of our list
            try:
                string = letters[li] + letters[li + 1]

                if string == current_state:

                    try: transitions.append(letters[li + 2])
                    except: pass

            except: pass

    return transitions

# returns a new state
def get_new_state(transition_list,current_state):

    '''
    :param transition_list: list of next letters, obtrained from the above
    :param current_state: current state
    :return: a new state
    '''

    new_character = '_'

    # we don't want to return an '_'
    # so we keep throwing a new letter until
    # it's not an '_' notice we select a transition
    # randomly by picking an index and selecting
    # that item from our transition, items that
    # appear more likely will show up more in the transition matrix
    while new_character == '_':
        idx = (randint(0,len(transition_list) - 1))
        new_character = transition_list[idx]

    return current_state + new_character

def get_name(min,max,gender):

    '''
    :param min: minimum length of the name
    :param max: maximum length of the name
    :param gender: m/f
    :return: prints a name
    '''

    # based on the user input, we randomly
    # select a length of a name to return
    # and when continue appending new
    # letters until our length is reached
    state = '__'
    name_length = randint(min,max)
    c = 0
    names = load_names(gender)

    while c < name_length:

        # pick a new letter
        passer = state[-2:]
        transitions = get_transition_probabilities(passer, names)
        state = get_new_state(transitions,state)
        c += 1

    print 'Name:', state.replace('_', '')


gender = raw_input('Is the gender m/f ?: ')
minimum_length = int(raw_input('What is the minimum length? : '))
maximum_length = int(raw_input('What is the maximum length? : '))
number_of_names = int(raw_input('How many names do you want returned? : '))

for i in range(0,number_of_names):
    get_name(minimum_length, maximum_length, gender)