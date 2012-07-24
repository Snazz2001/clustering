setwd("C:/My Projects/Zheng - Clustering/Data 2")
library(gregmisc)
setwd("C:/My Projects/Zheng - Clustering/Data 2")
postcodelatlon<-read.csv("postcodelonlat.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);
colnames(postcodelatlon)[1]<-"Code";
post<-read.csv("Postcodecleaned.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);


postcode2latlon<-function(postbat)
{
  post1<-unlist(strsplit(trim(postbat)," "))[1]
  latlon<-c(postbat,as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post1)),2]),as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post1)),3]));
  return(latlon)
}

latlon<-lapply(post$Postcode,postcode2latlon)
latlon2<-do.call("rbind",latlon)
