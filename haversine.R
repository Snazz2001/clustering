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
  
  