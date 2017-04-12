### Homework 2 Starter

## Working the bipartite network from the Beer Advocate data.

# ---- C1 ----
# Load libraries

library("ggplot2")
# Must load other packages first before SAND
library("sand")
library("intergraph")

# ---- C2 ---- 
# Load edge and attribute data
setwd("~/Documents/Craft-Repo/social_networks/homework_2")
beer.edges = read.csv("beer_edges.csv", stringsAsFactors = FALSE)
beer.nodes = read.csv("beer_attrs.csv", stringsAsFactors = FALSE)
head(beer.edges)
head(beer.nodes)

# ---- C3 ----
# Convert to graph
graph = graph.data.frame(beer.edges, vertices = beer.nodes, directed = FALSE)
summary(graph)

# ---- C4 ---- 
# Examine attributes
head(V(graph)$beer.name)

# ---- C5 ----
# Projections
V(graph)$beer.name[1:5]
V(graph)$type[1:5] # the beers are true
b2b.beer = bipartite.projection(graph, which = "true")
u2u.beer = bipartite.projection(graph, which = "false")
summary(b2b.beer) # 131 beers
summary(u2u.beer) # 147 users

# ---- C6 ----
# Remove non-user attributes
u2u.beer = remove.vertex.attribute(u2u.beer, "beer.name")
u2u.beer = remove.vertex.attribute(u2u.beer, "brewery")
u2u.beer = remove.vertex.attribute(u2u.beer, "alcohol")
summary(u2u.beer)

# ---- C7 ----
# Edge weight histogram
hist(E(u2u.beer)$weight, xlab = "Weight Bucket", ylab = "Edge Count",
     main = "Edge Weight Distribution", breaks = 5, col = "Blue")

# ---- C8 ----
# Filter edges of weight less than 6
u2u.beer.filt = delete.edges(u2u.beer, E(u2u.beer)[E(u2u.beer)$weight < 6])
summary(u2u.beer.filt)
 
# ---- C9 ----
# Remove singleton nodes
V(u2u.beer.filt)[degree(u2u.beer.filt) == 0]
u2u.beer.filt = delete.vertices(u2u.beer.filt, V(u2u.beer.filt)[degree(u2u.beer.filt) == 0])
summary(u2u.beer.filt)

# ---- C10 ----
# Plot
lo = layout_with_kk(u2u.beer.filt)
plot(u2u.beer.filt, layout = lo)

# ---- C11 ----
# Weighted degree histogram
wt = E(u2u.beer.filt)$weight
u2u.wt = graph.strength(u2u.beer.filt, vids = V(u2u.beer.filt), mode = "all", weights = wt, loops = FALSE)
hist(u2u.wt)

# ---- C12 ----
# Find 2 highest weighted degree individuals
V(u2u.beer.filt)[u2u.wt > 150]

# ---- C13 ----
# Create ego networks
which(V(u2u.beer.filt)$name == "Deuane") # 12
which(V(u2u.beer.filt)$name == "mikesgroove") # 26
egos = make_ego_graph(u2u.beer.filt, 1, c(12,26))
deuane = egos[[1]]
mikesgroove = egos[[2]]


# ---- C14 ----
# Plot ego networks
lo_deuane = layout_as_star(deuane, V(deuane)[5])
lo_mikesgroove = layout_as_star(mikesgroove, V(mikesgroove)[15])
par(mfrow = c(1,2))
plot(deuane, layout = lo_deuane)
plot(mikesgroove, layout = lo_mikesgroove)
par(mfrow = c(1,1))



