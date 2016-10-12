%Brian Craft | craft1.brian@gmail.com
%neural network csc578 project 1

function y = neural_network(nodeLayers, inputs, targets, batchSize, numEpochs, eta)

  %{

  Checklist

  Completed: 
    Evenything was completed
  Pseudocode  
    the pseudo code is in the comments of the function
  Well-documented
    there is pseudo code and documenation in the comments below as well as instructions
  Code works
    the code was run for all the examples and works like a charm
  Learning parameters: inputs, targets, nodeLayers, learning rate, epochs, batchSize
    all of the learning params were include, although eta represents learning rate
  Termination conditions: max epochs, correct classification
    if all the correct classificaitions are reached the function stops otherwise all epochs are done
  Mini-batch SGD
    this is performed
  Outputs as requested (iris, MNIST, and XOR)
    incldued in the word document
  Incomplete
    NA
  Not sure
    %NA
  
  inputs: a matrix with a column for each example, and a row for each input feature
  targets: a matrix with a column for each example, and a row for each output feature
  nodeLayers: a vector with the number of nodes in each layer (including the input and output layers). Important: Your code should not assume that there are just three layers of nodes. It should work with a network of any size.
  numEpochs: (scalar) desired number of epochs to run
  batchSize: (scalar) number of instances in a mini-batch
  eta: (scalar) learning rate
  y: the function returns a message at the end of the process, which is reached via epochs maxing out
  of all cases being correctly classified.  The function iteratively provides the epoch count, mse,
  cases predicted correctly and the accuracy.

  instructions

    nodeLayers is a list with the number of neurons in the corresponding layer
      
         [2,3,2] represents a 3 layer network with 2 input attributes and 2 classes

    inputs is the transpose of the data matrix, or attributes.

         | 1 0 1 0 | represents attribute 1
         | 0 1 1 0 | represetns attribute 2

    targets is a matrix where each column is an an observation and each row is a class, with 1s representing class membership
    binary can be treated as a row vector

         | 1 1 0 0| represents a 0 class

    batchSize is the size of the mini batch, the number of observation not the number of batches

         4

    epochs is the number of epochs to be run

         100

    eta is the learning rate

        .1

  test run using XOR

    nodeLayers = [2,3,2]
    inputs = [1 0 1 0 ;  0 1 1 0]
    targets = [0 0 1 1 ; 1 1 0 0]
    batchSize = 4
    numEpochs = 100
    eta = .1
    test = neural_network(nodeLayers, inputs, targets, batchSize, numEpochs, eta)

  %}

  %create a cell array that will store the weights and bias
  weights = {};
  bias = {};
    
  %counters used to make the weights and bias matrices
  to_layer = 2;
  from_layer = 1;

  %for layer l in l + 1, l + 2, l + 3...L, initialize weights and bias matrices with numbers 
  %with a mean of 0 and a standard distribution of 1 and 
  %the rows is the number of neurons in the l - 1 and the number of columns is the 
  %number of neurons in l + 1 (neurons in the to layer, neurons in the from layer)
  for layer = 2 : length(nodeLayers)
      weights{to_layer} = normrnd(0,1,nodeLayers(to_layer), nodeLayers(from_layer));
      bias{to_layer} = normrnd(0,1,nodeLayers(to_layer), 1);
      to_layer = 1 + to_layer;
      from_layer = 1 + from_layer;
  end

  %we will store the mini batches here
  batches = {};
  target_batches = {};
  counter = 1;


  %begin to subset inputs into mini batches based on the batchSize paramaters
  %if the remaining data is smaller then the batch size, return the remaining data as
  %the last batch
  for start_pos = 1 : batchSize : length(inputs)
      if length(inputs) - start_pos < batchSize
          mb = inputs(:, start_pos:end);
          batches{counter} = mb;
          target = targets(:, start_pos:end);
          target_batches{counter} = target;
      else
          mb = inputs(:, start_pos: start_pos + batchSize - 1);
          batches{counter} = mb;
          target = targets(:, start_pos: start_pos + batchSize - 1);
          target_batches{counter} = target;
          counter = counter + 1;
      end %end the if else
  end %end the input loop

  %begin running an epoch until the max number of epochs is reached or all cases are correctly classified
  for epoch = 1 : numEpochs

      %begin looping through each mini batch
      for batch = 1 : length(batches) 

          %store the intermediate values and activation here
          %initialize the first activaiton as the inputs from the mini batch
          %this is reset after each mini batch
          intermediate_z = {};
          activation = {};
          activation{1} = batches{batch};
          delta = {};

          %for layer 2 until L, calculate the intermediate z values 
          %by multiplying weights(layer) * activation(layer - 1) and column wise adding the bias
          %apply the sigmoid function element wise to the intermediate z matrix
          for layer = 2 : length(nodeLayers)
              intermediate_z{layer} = bsxfun(@plus,(weights{layer} * activation{layer - 1}), bias{layer});
              activation{layer} = logsig(intermediate_z{layer});
          end
              
          %calculate the error by subtracting the target values from the final activation values 
          %calculate the sigmoid prime of the intermediate values of the last layer which is sigmoid(zL) 
          %hadamard product 1 - sigmoid(zL)
          %calcualte the error of the final layer (deltaL) by taking error hadamard product of the sigprime(zL)                     
          err = (activation{length(nodeLayers)} - target_batches{batch});
          sigprime = logsig(intermediate_z{length(nodeLayers)}) .* (1 - logsig(intermediate_z{length(nodeLayers)}));
          delta{length(nodeLayers)} = err .* sigprime;

          %begin backpropogating the error for L - 1, L -2 .... 2
          for layer = (length(nodeLayers) - 1) : -1 : 2
              delta{layer} = (weights{layer + 1}.' * delta{layer + 1}) .* (logsig(intermediate_z{layer}) .* (1 - logsig(intermediate_z{layer})));
          end

          %for each layer L - 1, L - 2 ... 2 update the weights and biases using stochastic gradient decent
          %the weights and biases are updated based on the gradient and a ratio of eta/ the number of cases in
          %the mini batch
          for layer = length(nodeLayers) : -1 : 2
              w = weights{layer} - eta/length(batches{batch}) * delta{layer} * activation{layer - 1}.';
              weights{layer} = w;
              b = bias{layer} - eta/length(batches{batch}) * sum(delta{layer}, 2);
              bias{layer} = b;
          end

      end %end mini batch loop

      %after performing stochastic gradient descent using all mini batches an epoch is complete
      %the updated weights and biases are used to calculate the predicted (acitvaiton L) value on all the data
      final_activations = {};
      final_activations{1} = inputs;
      for layer = 2 : length(nodeLayers)
          z = bsxfun(@plus,(weights{layer} * final_activations{layer - 1}), bias{layer});
          a = logsig(z);
          final_activations{layer} = a;
      end

      %the predicted class is to be the max value of the column vectors which represent an observations
      %the predictions are taken against the real values and the correct cases are found
      if size(targets,1) > 1
          [it,vt] = max(targets);
          [i,v] = max(final_activations{length(nodeLayers)});
          confusion_array = vt - v;
          correct = sum(confusion_array(:)==0);
          accuracy = correct/length(inputs);

      %for binary classificaiton, we just round the value to a 0 or 1 thus providing the predicted
      %value of each case
      else
          confusion_array = targets - round(final_activations{length(nodeLayers)});
          correct = sum(confusion_array(:)==0);
          accuracy = correct/length(inputs);
      end

      %calculate the mse by using 1/2||y - yhat||^2 element wise then divided by 2n (provided in the book)
      mse = 1/(2*(length(inputs))) * sum(sum((.5 * (targets - final_activations{length(nodeLayers)}).^2)));
      fprintf('Epoch: %d MSE: %f5 Correct: %d/%d Accuracy: %f \n', epoch, mse, correct, length(inputs), accuracy);

      %after each epoch, if our correct cases is equal to our total cases then all cases
      %are correctly predicted and the function terminates otherwise a new epoch is started
      if correct == length(inputs)

          y = 'All cases were classified correctly';

          return;
      else
          continue; 
      end

  end %end epoch loop if needed

  y = 'The number of epochs was reached';

end %end function


%{

psedo code (see comments in code for more description)

initialize weights and biases for layers L - 1

for epoch

  for mini batch

    set the initial activation to the mini batch data

    for each layer in 2...L
      calculate intermediate values and activations using the sigmoid

    for each layer in L...2
      calculate and backpropogate the error to biases and weights for each L until layer 2

    for each layer 2...L
      update weights and biases using the gradients

  calcualte rsme on the input data with the updated weights and biases after each epoch

  if accuracy is 100%
    terminate

  else
    continue to new epoch

end

%}


