#CSC 424 Assignment 3

#Set working dicretory
setwd("~/Dropbox/Grad School/CSC 424/Assignment 3")

#problem 2
#box m test, high p value here
beetle <- read.table("~/Dropbox/Grad School/CSC 424/Assignment 3/beetle.txt", quote="\"", comment.char="")
colnames(beetle) = c('species', 'thorax', 'elytra', 'aj2', 'aj3')

require(MASS)
require(biotools)

#equality of variance between the two species, part a
boxM(beetle[,2:5], beetle[,1])

#lda function, part b
lda = lda(species ~ ., data = beetle, prior = c(.5,.5))
lda$scaling
lda$prior
lda$counts
lda$means
lda$prior
lda_predict = data.frame(predict(lda, data = beetle[,2:5]))
lda_a = subset(lda_predict, lda_predict$class == "a")
lda_b = subset(lda_predict, lda_predict$class == "b")
summary(lda_a$LD1)
summary(lda_b$LD1)
mid = (mean(lda_b$LD1) - abs(mean(lda_a$LD1)))/2
       
prop = lda$svd^2/sum(lda$svd^2)
prop

#classify, part c
vector = data.frame(184,275,143,192)
colnames(vector) = c('thorax', 'elytra', 'aj2', 'aj3')
predict(lda,newdata = vector)

#predict, get confusion matrix, part d
class_true = beetle[,1]
pred_data = beetle[,2:5]
class_pred = data.frame(predict(lda, newdata = pred_data))
true_pred = cbind(class_true, class_pred)
confusion = table(true_pred[,1], true_pred[,2])
confusion

#cross validation
lda_cv = lda(species ~ ., data = beetle, prior = c(.5,.5), CV = TRUE)
pred_cv = data.frame(lda_cv$class)
pred_true_cv = data.frame(cbind(pred_cv, true_pred))
confusion_cv = table(pred_true_cv[,1], pred_true_cv[,3])
confusion_cv

#problem 3

#import the data
faculty <- read.csv("~/Dropbox/Grad School/CSC 424/Assignment 3/faculty.csv")

#lda for faculty, problem 3
faculty_trim = faculty[,c(4,20:31)]
lda_faculty = lda(facrank ~ ., data=faculty_trim)
lda_faculty$scaling
lda_faculty$prior

#get the equation
predict_faculty = data.frame(predict(lda_faculty, newdata = faculty_trim[,2:13]))
plot(predict_faculty$x.LD1, predict_faculty$x.LD2)

#k means clustering, part a
k_cluster = kmeans(faculty_trim[,2:13], 4)

#get the centers to plot the parallel coordinates
require(lattice)
centers = data.frame(k_cluster$centers)
parallelplot(centers, horizontal.axis = FALSE)

k_cluster$withinss
k_cluster$size

#get the cluster assignments
cluster_df = data.frame(k_cluster$cluster)
class = faculty_trim[,1]
class_cluster = data.frame(cbind(cluster_df, faculty_trim))  
confusion = table(class_cluster[,1], class_cluster[,2])
confusion

#hierarchical clustering, part b
h_cluster = hclust(dist(faculty_trim[,2:13]), method = 'single')
plot(h_cluster)

#trim the tree and assign the classes to the points
cut = cutree(h_cluster,4)
table(cut, faculty_trim[,1])
faculty_trim$class = cut
