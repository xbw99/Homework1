#include <stdio.h>

behavior Sender (out event e1, out char ch, in event Ack)
{
	char A;
	char input[11] = "Helloworld";
	int i;
	void main(void)
	{
		for(i = 0; i < 10; i++) {
			A = input[i];
			ch = A;
			printf("Sending \'%c\'\n", A);
			notify e1;
			wait Ack;
		}
		
		
	}
};

behavior Receiver (in event e1, in char ch, out event Ack)
{
	char B;
	int j;
	void main(void)
	{
		
		for(j = 0; j < 10; j++) {
			wait e1;
			B = ch;
			notify Ack;
			printf("Received \'%c\'\n", B);
		}
	}
};

behavior Main(void)
{
	event e1, Ack;
	char ch;
	Sender S(e1, ch, Ack);
	Receiver R(e1, ch, Ack);
	int main(void)
	{
		printf("Receiver starting...\nSender starting...\n");
		par {
				S.main();
				R.main();
			}
		return 0;
	}
};

