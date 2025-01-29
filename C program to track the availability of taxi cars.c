#include <string.h>
#include <stdio.h>
#include <stdlib.h>


struct Taxi{
   int id;
   char driver[20];
   char category[20];
   char plate[7];
   char color[20];
   float rate;
   float minCharge;
   char state;
   struct Taxi *next;
};

struct Taxi *list=NULL;
 
 //////////////////////////////////////////////////////////////////////////////////////////////////
//method#1

void addTripCar(){
   struct Taxi *head =NULL;
   struct Taxi *current ;
   FILE *f_taxi ;
   f_taxi= fopen ("Taxies.txt","r");
   if (f_taxi==NULL){
      printf("EROR IN OPINING FILE");
      return;}

   int NO_line=0;      // Calculate the number of line in file 
   int IsEOF;
   while ((IsEOF=getc(f_taxi))!=EOF)
      if(IsEOF=='\n')
         NO_line++;
   fclose(f_taxi);

   f_taxi= fopen ("Taxies.txt","r");
   if (f_taxi==NULL)
      return;

   char c;
   while((c=getc(f_taxi))!='\n');


   int i ; 
   for( i = 0 ; i < NO_line-1 ; i++){
      struct Taxi *temp = (struct Taxi *) malloc(sizeof(struct Taxi));
      fscanf(f_taxi, "%d %s %s %s %s %f %f ", &(temp->id) , temp->driver , temp->category , temp->plate , temp->color , &(temp->rate) , &(temp->minCharge) );
      temp->state = 'A' ; 
      temp->next = NULL; 
      if( head == NULL){
         head = temp ;
         current = head ; 
      }
      else
      {
         current->next = temp ; 
         current = current->next ; 
      }
   }// end for 

   list = head ; 
   fclose(f_taxi);
}
 

 //////////////////////////////////////////////////////////////////////////////////////////////////
//method#2

void setTripCar(char *category, float *rate){


   if (list==NULL){
      printf("\n The car list is empty! \n");
      return;
   }

   struct Taxi *current;
   current=list;

   while (current != NULL){
      if (strcmp(current->category,category)==0 && current->rate == *rate)  {
         current->state='R';
         return;
      }
      else
         current=current->next;
   }

}

//////////////////////////////////////////////////////////////////////////////////////////////////
//method#3

void writeCarsInRide(char*fileName){

   if (list==NULL){
      printf("\n The car list is empty! \n");
      return;
   }
 
   FILE *f_taxies;
   f_taxies=fopen(fileName,"a");
 
   if ( f_taxies==NULL){
      printf("EROR IN OPINING FILE");
      return;
   }

   struct Taxi *current;
   current=list;

   fprintf(f_taxies,"\n---------------------------------------------------------------------------------------------------------------\n");
   fprintf(f_taxies,"the cars in Ride:\n");
   fprintf(f_taxies,"%-15s\t%-15s\t%-20s\t%-20s\t%-15s\t%-20s\n","id","driver","category","plate","rete","state");

   while(current!=NULL){
      if(current->state=='R')
         fprintf(f_taxies,"%-15d\t%-15s\t%-20s\t%-20s\t%-15.1f\t%c\n",current->id , current->driver , current->category , current->plate , current->rate , current->state);
      current = current->next;
   
   }
   fclose(f_taxies);
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//method#4

void printList(){

   if (list==NULL){
      printf("\n The car list is empty! \n");
      return;
   }
 
   struct Taxi *current;
   current=list;

   while(current!=NULL){
      printf("%-5d\t%-15s\t%-15s\t%-10s\t%-5.1f\t%-5.2f\t%c\n", (current->id) , current->driver , current->category , current->plate , (current->rate) , (current->minCharge) , current->state);
      current = current->next;
   }
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//main

int main(){
   addTripCar();
   printf("The Available Trip Cars:\n");
   printList(); 
   char *cate1 = "Business" ; float rate1 = 4.5 ; 
   char *cate2= "Family" ; float rate2 = 5.0 ; 
   char *cate3= "Family" ; float rate3 = 4.0 ;
   char *cate4= "standard" ; float rate4 = 3.4 ; 
   char *cate5= "standard" ; float rate5 = 5.0 ;
   setTripCar( cate1 , &rate1 ); 
   setTripCar( cate2 , &rate2 ); 
   setTripCar( cate3 , &rate3 ); 
   setTripCar( cate4 , &rate4 ); 
   setTripCar( cate5 , &rate5 ); 

   printf("\n----------------------------------------------------------------------------\n");
   printf("the Cars in Ride:\n"); 
   printList(); 

   char *fileName = "Taxies.txt";
   writeCarsInRide(fileName);

   return 0;
}