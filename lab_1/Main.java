import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;
import java.text.*;
public class main
{
    public static void main(String[] args)
    {
        Teilor A = new Teilor();
        A.Read();
        A.Count();
        A.PrintResult();
    }
}

class Teilor
{
    BigInteger k;
    BigDecimal x;
    BigDecimal result;
    public void Read()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Ведите точность (k) и значение (x): ");
        k = BigInteger.valueOf(in.nextInt());
        x = BigDecimal.valueOf(in.nextDouble());
    }

    public void Count()
    {
        BigDecimal e = BigDecimal.valueOf(Math.pow(10, (-k.doubleValue())));
        BigDecimal a = x;
        result = x;
        BigDecimal d = BigDecimal.valueOf(3);
        BigDecimal f = BigDecimal.valueOf(1);
        while(a.abs().compareTo(e) == 1)
        {
            a = BigDecimal.valueOf(Math.pow(x.doubleValue(), d.doubleValue()));
            a = a.divide(d,k.intValue() + 1, RoundingMode.HALF_UP);
            f = BigDecimal.valueOf(1);
            for(double j = 1, t = 2; j < d.doubleValue(); j+=2, t+=2)
            {
                f = f.multiply(BigDecimal.valueOf(j/t));
            }
            a = a.multiply(f);
            d = d.add(BigDecimal.valueOf(2));
            result  = result.add(a);
        }
    }

    public void PrintResult()
    {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(k.intValue() + 1);
        System.out.println("Результат вычислений: " + formatter.format(result.doubleValue()));
        double temp = Math.asin(x.doubleValue());
        System.out.println("Результат стандартной библиотеки:" + formatter.format(temp));
System.out.format("k hex: %x %nk octal: %o %n", k, k);
        System.out.format("sum: %010f %n", sum);
        System.out.format("func: %#.5f %n", ln);
        System.out.format("%+3$(,d %1$d %2$d", -1, 54, 13);
    }
}