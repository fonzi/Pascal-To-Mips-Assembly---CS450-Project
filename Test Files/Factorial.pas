program Factorial;
{The program computes 0! through 170!}
var
i, x : integer; {''i'' here stands for iteration}
fact : real; {Usually factorials are so large that we can't use integer here}
begin
writeln ('Enter the value of the factorial');
readln (x);
fact:=1; {As we can't multiply by 0, our first number should be 1}
for i:=1 to x do  {Here is our loop}
fact :=fact*i;
writeln(x,'! is ',fact);
readln; {The semicolon is not obligatory here}
end.
