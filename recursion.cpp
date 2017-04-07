#include<iostream>
#include<stdlib.h>
#include<cstdio>

using namespace std;

int number;
int a[99999]={0};

void recursion(int t1,int t2)
{
	if(t1>=t2)
	{
		/*for(int i=1;i<=number;i++)
		{
			cout<<a[i]<<" ";
		}
		cout<<endl;*/
	}
	else
	{
		for(int k=t1;k<=t2;k++)
		{
			int temp;
			temp=a[t1];
			a[t1]=a[k];
			a[k]=temp;
			recursion(t1+1,t2);
			temp=a[t1];
			a[t1]=a[k];
			a[k]=temp;
		}
	}
}

int main()
{
	number=5;
	for(int i=1;i<=number;i++)
	{
		a[i]=i;
	}
	recursion(1,number);
	//system("pause");
	return 0;
}

