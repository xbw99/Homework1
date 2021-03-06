#include <stdio.h>

interface TwoWayHandShaking
{
	void Send(char c);
	char Receive(void);
};

channel HandShaking implements TwoWayHandShaking
{
	char	Data;
	event	Valid,
		Ack;

	void Send(char c)
	{
		Data = c;
		notify Valid;
		wait Ack;
	}

	char Receive(void)
	{
		char c;
		wait Valid;
		c = Data;
		notify Ack;

		return c;
	}
};

behavior Sender(TwoWayHandShaking Port)
{
	char word[11] = "Helloworld";
	void main(void)
	{
	int	i;
	char	s;

	for(i=0; i<10; i++)
	{
		s = word[i];
		Port.Send(s);
		printf("Sending \'%c\'\n", s);
	}
	
	}
};

behavior Receiver(TwoWayHandShaking Port)
{
	void main(void)
	{
	int	i;
	char	s;

	for(i=0; i<10; i++)
	{
		s = Port.Receive();
		printf("Received \'%c\'\n", s);
	}
	
	}
};

behavior Main(void)
{
	HandShaking	C;
	Sender		S(C);
	Receiver	R(C);

	int main(void)
	{
	printf("Receiver starting...\nSender starting...\n");
	par {	S.main();
		R.main();
		}
	
	return 0;
	}
};
