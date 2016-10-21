%Brian Craft | craft1.brian@gmail.com
%neural network csc578 project 1

function [mse_train_array,mse_val_array,mse_test_array,accuracy_train_array,accuracy_val_array,accuracy_test_array] = neural_network(nodeLayers, inputs, targets, batchSize, numEpochs, eta, test_validation)

  %Checklist
  %[mse_train_array,mse_val_array,mse_test_array,accuracy_train_array,accuracy_val_array,accuracy_test_array] = neural_network(nodeLayer, iris_x.', iris_y.', batchSize, epochs, eta, test_validation)
  
  %Test and Validation: vector [80,10,10]
  %split inputs
  [train_ind,val_ind,test_ind] = dividerand(length(inputs),test_validation(1),test_validation(2),test_validation(3));
  input_train = inputs(:,train_ind);
  input_val = inputs(:,val_ind);
  input_test = inputs(:,test_ind);
  
  %pass the indicies to the targets as well
  target_train = targets(:,train_ind);
  target_val = targets(:,val_ind);
  target_test = targets(:,test_ind);
  
  %Return accuracy and cost on each data partition (test, training, validation) after each epoch and so plots can be made
  epoch_array = [];
  mse_train_array = [];
  mse_val_array = [];
  mse_test_array = [];
  
  accuracy_train_array = [];
  accuracy_val_array = [];
  accuracy_test_array = [];
  
  %{
  Early Stopping: When error/cost on the validation/test increases after
  some number of epochs

  Cost: Quadratic, Cross Entropy, Log Likelinhood

  Momentum

  L2 Regularizaion

  Better Initial Weights

  Functions: Tanh, Softmax, reLu

  Mini Batch Suffling: At the beggining of the epochs, shuffle the mini
  batches in the training

  Adaptive Learning Rate: 

  Return the learned network



  
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
  for start_pos = 1 : batchSize : length(input_train)
      if length(input_train) - start_pos < batchSize
          mb = input_train(:, start_pos:end);
          batches{counter} = mb;
          target = target_train(:, start_pos:end);
          target_batches{counter} = target;
      else
          mb = input_train(:, start_pos: start_pos + batchSize - 1);
          batches{counter} = mb;
          target = target_train(:, start_pos: start_pos + batchSize - 1);
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
      final_activations{1} = input_train;
      for layer = 2 : length(nodeLayers)
          z = bsxfun(@plus,(weights{layer} * final_activations{layer - 1}), bias{layer});
          a = logsig(z);
          final_activations{layer} = a;
      end
      
      %run model through the validation set
      final_activations_val = {};
      final_activations_val{1} = input_val;
      for layer = 2 : length(nodeLayers)
          z = bsxfun(@plus,(weights{layer} * final_activations_val{layer - 1}), bias{layer});
          a = logsig(z);
          final_activations_val{layer} = a;
      end
      
      %run model through the test data
      final_activations_test = {};
      final_activations_test{1} = input_test;
      for layer = 2 : length(nodeLayers)
          z = bsxfun(@plus,(weights{layer} * final_activations_test{layer - 1}), bias{layer});
          a = logsig(z);
          final_activations_test{layer} = a;
      end
      
    
      %the predicted class is to be the max value of the column vectors which represent an observations
      %the predictions are taken against the real values and the correct cases are found
      if size(target_train,1) > 1
          [it,vt] = max(target_train);
          [i,v] = max(final_activations{length(nodeLayers)});
          confusion_array = vt - v;
          correct = sum(confusion_array(:)==0);
          accuracy = correct/length(input_train);
          
      %validation set
      if size(target_train,1) > 1
          [it,vt] = max(target_val);
          [i,v] = max(final_activations_val{length(nodeLayers)});
          confusion_array = vt - v;
          correct_val = sum(confusion_array(:)==0);
          accuracy_val = correct_val/length(input_val);
          
      %test set
      if size(target_train,1) > 1
          [it,vt] = max(target_test);
          [i,v] = max(final_activations_test{length(nodeLayers)});
          confusion_array = vt - v;
          correct_test = sum(confusion_array(:)==0);
          accuracy_test = correct_test/length(input_test);
          
      %for binary classificaiton, we just round the value to a 0 or 1 thus providing the predicted
      %value of each case
      else
          confusion_array = target_train - round(final_activations{length(nodeLayers)});
          correct = sum(confusion_array(:)==0);
          accuracy = correct/length(input_train);
      end
      
      %val
      else
          confusion_array = target_val - round(final_activations_val{length(nodeLayers)});
          correct_val = sum(confusion_array(:)==0);
          accuracy_val = correct_val/length(input_val);
      end

      %test
      else
          confusion_array = target_test - round(final_activations_test{length(nodeLayers)});
          correct_test = sum(confusion_array(:)==0);
          accuracy_test = correct/length(input_test);
      end

      %calculate the mse by using 1/2||y - yhat||^2 element wise then divided by 2n (provided in the book)
      mse = 1/(2*(length(input_train))) * sum(sum((.5 * (target_train - final_activations{length(nodeLayers)}).^2)));
      mse_val = 1/(2*(length(input_val))) * sum(sum((.5 * (target_val - final_activations_val{length(nodeLayers)}).^2)));
      mse_test = 1/(2*(length(input_test))) * sum(sum((.5 * (target_test - final_activations_test{length(nodeLayers)}).^2)));
      fprintf('Epoch: %d MSE Train: %f5 Correct Train: %d/%d Accuracy Train: %f \n', epoch, mse, correct, length(input_train), accuracy);
      fprintf('Epoch: %d MSE Train: %f5 Correct Train: %d/%d Accuracy Train: %f \n', epoch, mse_val, correct_val, length(input_val), accuracy_val);
      fprintf('Epoch: %d MSE Train: %f5 Correct Train: %d/%d Accuracy Train: %f \n', epoch, mse_test, correct_test, length(input_test), accuracy_test);
      disp(' ')
      
      %append values to the arrays that store accuracies after epochs
      epoch_array(epoch) = [epoch];
      mse_train_array(epoch) = [mse];
      mse_val_array(epoch) = [mse_val];
      mse_test_array(epoch) = [mse_test];
      
      accuracy_train_array(epoch) = [accuracy];
      accuracy_val_array(epoch) = [accuracy_val];
      accuracy_test_array(epoch) = [accuracy_test];
      
      %after each epoch, if our correct cases is equal to our total cases then all cases
      %are correctly predicted and the function terminates otherwise a new epoch is started
      if correct == length(input_train)

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


