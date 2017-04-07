#include<cstdlib>
#include<cstdio>
#include<iostream>
#include<stdlib.h> 

using namespace std;

int array[99999];
int used[99999];
int number;

void backtracking(int k)
{
	if(k>number)
	{

		/*for(int i=1;i<=number;i++)
		{
			cout<<array[i]<<" ";
		}
		cout<<endl;*/
	}
	else
	{
		for(int i=1;i<=number;i++)
		{
			if(used[i]==0)
			{
				used[i]=1;
				array[k] = i;
				backtracking(k+1); 
				used[i] = 0;
			}
		}
	}
}
int main()
{
	//cout<<"please input the number"<<endl;
  	//cin>>number;
  	number=5;
	for(int i=1;i<=number;i++)
	{
		used[i]=0;
	}
  	backtracking(1);
	//system("pause");
	return 0;
}

