merge<-hc_op$merge;
index <- seq(1:dim(merge)[1]);
parentid<- rep(0,dim(merge)[1]);
size<- apply(merge,1,function(x) sum(x<0));
sumsocre<- rep(0,dim(merge)[1]);
merge<- cbind(merge,index);
merge<- cbind(merge,parentid);
merge<- cbine(merge,size);
pid<-0;
cid<-0;
paths<-c();

for(i in seq(dim(merge)[1],1,-1))#impute the parent node id;
{
  if(merge[i,2]>0)
  {
    merge[merge[i,2],'parentid']<-i;
  }
  if(merge[i,3]>0)
  {
    merge[merge[i,3],'parentid']<-i;
  }
}

#for(i in seq(1,dim(merge)[1],1))#compute the size
#{
#       merge[merge[i,4],5] <- merge[merge[i,4],5] + merge[i,5];
#}

for(i in seq(1:dim(merge)[1]))
{
  if(merge[i,1]<0)
  {
    path<-paste("app",as.character(-merge[i,1]));
    cid<-merge[i,3];
    pid<-merge[i,4];
    key<- str_c(path,",",cid)
    loanAmount$key<- Clustering_Windowed_input[-merge[i,1],12];
    mixScore$key, Clustering_Windowed_input[-merge[i,1],10];
  #  path<-paste(pid,cid,path,Clustering_Windowed_input[-merge[i,1],12],Clustering_Windowed_input[-merge[i,1],10],sep=",");
    while(pid<dim(merge)[1])
    {
      pid <- merge[pid,4];
      path<- paste(pid,path,sep=",");
    }
    paths<-c(paths,path);
  }
  if(merge[i,2]<0)
  {
    path<-paste("app",as.character(-merge[i,2]))
    cid<-merge[i,3];
    pid<-merge[i,4];
    path<-paste(pid,cid,path,Clustering_Windowed_input[-merge[i,2],12],Clustering_Windowed_input[-merge[i,2],10],sep=",");
    while(pid<dim(merge)[1])
    {
      pid <- merge[pid,4];
      path<- paste(pid,path,sep=",");
    }
    paths<-c(paths,path);
  }
}



currentid<-as.character(merge[,3])
parentid<-as.character(merge[,4])
size<-(merge[,5])

treemap<-data.frame(parentid,currentid,size);
treemap[rev(1:dim(treemap)[1]),]
write.csv(treemap,file="treemap.csv")

merge<-read.csv("Mergestructure.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);
Cluster_window<-read.csv("ClusteringWindowInput_for_treemap.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);
Cluster_window$Score[which(is.na(Cluster_window$Score))]<- -1
Cluster_window$LoanAmont[which(is.na(Cluster_window$LoanAmont))]<- 0

write.csv(Cluster_window,file="ClusteringWindowInputClean.csv")