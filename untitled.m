activation_function = 'sdsds'


if strcmp(activation_function, 'tanh') == 1
    disp(tanh(testx))

elseif strcmp(activation_function, 'sigmoid') == 1
                  disp(logsig(testx))
                  
              else
                  poslin(testx)
                  
              end