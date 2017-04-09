# Example 2
# CSC 495

# ---- C1 ----
library("ggplot2")
# Must load other packages first
library("sand")
library("intergraph")

# ---- C2 ---- 
# Load data
setwd("~/Documents/Craft-Repo/social_networks/example_2")
davis = read.graph("davis.graphml", format = "graphml")

# ---- C3 ----
# Examine data
summary(davis)

# ---- C4 ----
# Plot using bipartite layout
lo = layout.bipartite(davis)
lo2 = cbind(lo[,2],lo[,1])
plot(davis, layout = lo2)

# ---- C5 ----
# Look at labels and types
V(davis)$label[1:5]
V(davis)$type[1:5]

# ---- C6 ----
# Create person-person projection
davis.p2p = bipartite.projection(davis, which = "false")

# ---- C7 ----
# Plot the projected graph
plot(davis.p2p)

# ---- C8 ----
# Plot the distribution of edge weights
davis.wts = E(davis.p2p)$weight
hist(davis.wts)

# ---- C9 ----
# Create a new network removing the edges of weight 1 and 2.
davis.filt = delete.edges(davis.p2p, 
                          E(davis.p2p)[E(davis.p2p)$weight <= 2])
plot(davis.filt, layout = layout.kamada.kawai(davis.filt))

# ---- C10 ----
# Plot the weighted degree distribution of the new network
davis.deg = graph.strength(davis.filt)
p = ggplot(data.frame(Degree = davis.deg), aes(x = Degree))
p = p + geom_histogram(breaks = seq(0,50), col = "white")
print(p)

# ---- C11 ----
# Extract the ego networks for Laura and Sylvia
which(V(davis.filt)$label == "Laura")
which(V(davis.filt)$label == "Sylvia")
egos = make_ego_graph(davis.filt, 1, c(2,13))
laura = egos[[1]]
sylvia = egos[[2]]

# ---- C12 ----
# Plot side-by-side (use par(mfrow...) to do this). 
lo_laura = layout_as_star(laura)
lo_sylvia = layout_as_star(sylvia)
par(mfrow = c(1,2))
plot(laura, layout = lo_laura)
plot(sylvia, layout = lo_sylvia)
par(mfrow = c(1,1))

# ---- C13 ----
# Create comparative data frame 

# ---- C14 ----
# Plot histogram

# ---- C15 ----
# Edge and attribute data
edge.df = read.csv("edgelist-sample.csv", stringsAsFactors = FALSE)
node.df = read.csv("nodeattr-sample.csv", stringsAsFactors = FALSE)
edge.df
node.df

# ---- C16 ----
# Convert to graph
graph = graph.data.frame(edge.df, vertices = node.df, directed = FALSE)

# ---- C17 ----
# Plot
plot(graph)

