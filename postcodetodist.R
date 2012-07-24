setwd('C:/My Projects/Zheng - Clustering/Data/')
postcodelonlat<-read.csv("postcodelonlat.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);
colnames(postcodelonlat)[1]<-'Code'

postcode2latlon<-function(post)
{
  post<-unlist(strsplit(trim(post)," "))[1]
  latlon<-c(as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post)),2]),as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post)),3]));
  return(latlon)
}


haversine<-function(x,y)
{
  lati1<-as.numeric(x[1]*pi/180);
  long1<-as.numeric(x[2]*pi/180);
  lati2<-as.numeric(y[1]*pi/180);
  long2<-as.numeric(y[2]*pi/180);
  dellati <- lati1 - lati2;
  dellong <- long1 - long2;
  a <- sin(dellati/2)^2 + cos(lati1)*cos(lati2)*sin(dellong/2)^2;
  c <- 2*atan2(sqrt(a),sqrt(1-a));
  return(6371*c)  
}
  
a<-' e14 9uu '
b<-"bs1 5qr"
a<-postcode2latlon(a)
b<-postcode2latlon(b)
heversine(a,b)

l0apps<-read.csv("Jan2011_AllL0_Apps.csv",header=TRUE,sep=",",na.strings="NULL",stringsAsFactors=FALSE);

latlon<-lapply(l0apps$Postcode,postcode2latlon)

ind<-c();#to find the post code dose not find in database
for(i in 1:5000)
{
  if(identical(latlon[[i]],numeric(0)))
  {
    ind<-c(ind,i)
  }
}
post[149,'Post.Code']<-'PO8 0XP';#correct the error
latlon[[149]]<-c(50.9371441,-0.9955751);#find the right long lat from PostcdeToLonlat.java using Googla Map API
latlon2<-do.call("rbind",latlon)
colnames(latlon2)<-c("lat","long");

latlons<-c();
for(i in 1:dim(l0apps)[1])
{
	post1<-unlist(strsplit(trim(l0apps[i,'Postcode'])," "))[1];
 latlon<-c(l0apps[i,'id'],as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post1)),2]),as.numeric(postcodelatlon[which(postcodelatlon$Code==toupper(post1)),3]));	
	latlons<-rbind(latlons,latlon);

}

colnames(latlons)<-c('id','lat','long')
l0<-merge(l0apps,latlons,by.x='id')
l0[which(l0$lat>70),]
l0cor<-l0[which(l0$lat<70),]

distance<-matrix(data=rep(0,25000000),nrow=5000);
for(i in 1:5000)
{
	for(j in i:5000)
	{
		distance[i,j] = haversine(l0cor5000_2[i,'lat'],l0cor5000_2[i,'long'],l0cor5000_2[j,'lat'],l0cor5000_2[j,'long'])	
	}
}

