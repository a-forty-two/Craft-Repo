#CSC 424 Assignment 2

#Set Working Directory
setwd("~/Dropbox/Grad School/CSC 424/Assignment 2")

#Problem 1

  #Load the data
  housing <- read.table("~/Dropbox/Grad School/CSC 424/Assignment 2/housing.data", quote="\"", comment.char="")
  
  #relable column names
  colnames(housing)[1] = "crim"
  colnames(housing)[2] = "zn"
  colnames(housing)[3] = "indus"
  colnames(housing)[4] = "chas"
  colnames(housing)[5] = "nox"
  colnames(housing)[6] = "rm"
  colnames(housing)[7] = "age"
  colnames(housing)[8] = "dis"
  colnames(housing)[9] = "rad"
  colnames(housing)[10] = "tax"
  colnames(housing)[11] = "ptratio"
  colnames(housing)[12] = "bk"
  colnames(housing)[13] = "lsat"
  colnames(housing)[14] = "medv"
  
  #part 1 create regression model
  lm_housing = lm(housing$medv ~ ., data = housing)
  summary(lm_housing)
  
  #part b, stepwise forward selection
  require(MASS)
  null_step = lm(housing$medv ~ 1, data = housing)
  
  #find variables between the full and null model
  stepper = step(null_step,scope=list(lower=null_step, upper=lm_housing), direction = "forward")
  stepper$anova
  summary(stepper)

#Problem 2
  #load the data
  marsh <- read.csv("~/Dropbox/Grad School/CSC 424/Assignment 2/data_marsh_cleaned_hw2.csv")
  
  #part a
  require(CCA)
  require(GGally)
  require(ggplot2)
  require(yacca)
  
  #variables sets
  water_var = marsh[,2:6]
  soil_var = marsh[,7:9]
  
  #correlation matrix
  ggpairs(water_var)
  ggpairs(soil_var)
  
  #correlation
  matcor(water_var,soil_var)
  
  #cononical version 1
  cc_water_soil = cc(water_var,soil_var)
  
  #canonical version 2
  cca_water_soil = cca(water_var, soil_var)
  F.test.cca(cca_water_soil)
  
  #display the connonical correlations
  cc_water_soil$cor
  
  #raw canonical coefficients
  cc_water_soil[3:4]
  
  #get the loadings
  cc_water_soil_2 = comput(water_var,soil_var,cc_water_soil)
  
  #display the loadings
  cc_water_soil_2[3:6]
  
  #f measure, method 1 
  eigen_val = (1-cc_water_soil$cor^2)
  n = dim(water_var)[1]
  p = length(water_var)
  q = length(soil_var)
  k = min(p,q)
  m = n - 3/2 - (p + q)/2
  w = rev(cumprod(rev(eigen_val)))
  
  d1 <- d2 <- f <- vector("numeric", k)
  
  for (i in 1:k) {
    s <- sqrt((p^2 * q^2 - 4)/(p^2 + q^2 - 5))
    si <- 1/s
    d1[i] <- p * q
    d2[i] <- m * s - p * q/2 + 1
    r <- (1 - w[i]^si)/w[i]^si
    f[i] <- r * d2[i]/d1[i]
    p <- p - 1
    q <- q - 1
  }
  
  pv <- pf(f, d1, d2, lower.tail = FALSE)
  (dmat <- cbind(WilksL = w, F = f, df1 = d1, df2 = d2, p = pv))
  
#problem 3
  
  #load the data
  pca <- read.delim("~/Dropbox/Grad School/CSC 424/Assignment 2/problem3.txt")

  #normalize the variables
  pca$agr_z = scale(pca$Agr)
  pca$min_z = scale(pca$Min)
  pca$man_z = scale(pca$Man)
  pca$ps_z = scale(pca$PS)
  pca$con_z = scale(pca$Con)
  pca$si_z = scale(pca$SI)
  pca$fin_z = scale(pca$Fin)
  pca$sps_z = scale(pca$SPS)
  pca$tc_z = scale(pca$TC)
  
  pca_df = pca[,2:10]
  
  #principle component analysis
  pca_model = prcomp(pca_df, center = FALSE)
  
  #print results
  print(pca_model)
  
  #plot results
  plot(pca_model, type = "l")
  
  #summary of the pca model
  summary(pca_model)
  
  #create the prediction model
  pca_pred = pca[,c(1,11:19)]
  data = data.frame(predict(pca_model, pca_pred[2:10]))
  data_1 = cbind(pca_pred[,1], data)
  
  #create scatter plot
  plot(data_1$PC1, data_1$PC2,
       main = "PC1 vs. PC2",
       xlab = "Principle Component 1",
       ylab = "Principle Component 2",
       col = "Blue")
  
  require(calibrate)
  textxy(data_1$PC1, data_1$PC2, data_1$`pca_pred[, 1]`, m = c(-2,-2))
