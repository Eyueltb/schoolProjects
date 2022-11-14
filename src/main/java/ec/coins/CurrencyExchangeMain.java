package ec.coins;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CurrencyExchangeMain {
    static IExchangeService exchangeService = new ExchangeServiceImpl();

    public static void main(String[] args) {

        List<Coin> myWallet = new LinkedList<>();

        myWallet.add(Krona.EN_KRONA);
        myWallet.add(Krona.EN_KRONA);
        myWallet.add(Krona.EN_KRONA);
        myWallet.add(Krona.FEM_KRONA);
        myWallet.add(Krona.FEM_KRONA);
        myWallet.add(Krona.TIO_KRONA);
        myWallet.add(Krona.TIO_KRONA);
        myWallet.add(Krona.TIO_KRONA);
        myWallet.add(Krona.KVÅ_KRONA);
        myWallet.add(Krona.KVÅ_KRONA);
        myWallet.add(Krona.KVÅ_KRONA);
        myWallet.add(Krona.KVÅ_KRONA);
        myWallet.add(Krona.KVÅ_KRONA);

        List<Coin> myExchangedCoins = exchangeService.exchange(myWallet.stream(), Currency.SEK, Currency.EUR)
                .collect(Collectors.toList());

        myExchangedCoins.forEach(coin -> System.out.println("Main.main "+coin.getName()));

        System.out.println("-------------------------");

        Stream<Coin> stream = exchangeService.exchange(myExchangedCoins.stream(), Currency.EUR, Currency.SEK);

        stream.forEach(coin -> System.out.println("Main.main "+coin.getName()));
    }
}
interface Coin {
    Currency getCurrency();
    double getValue();
    String getName();
    static Comparator<Coin> sortByValueDesc() {
        return Comparator.comparingDouble(Coin::getValue).reversed();
    }
}


interface IExchangeService {
    Stream<Coin> exchange(Stream<Coin> stream, Currency from, Currency to);
}

enum Currency {
    SEK,
    EUR
}

enum Euro implements Coin {
    TWO_EURO(2),
    ONE_EURO(1),
    FIFTY_CENT(0.5),
    TWENTY_CENT(0.2),
    TEN_CENT(0.1),
    FIVE_CENT(0.05),
    TWO_CENT(0.02),
    ONE_CENT(0.01);

    double value;
    Euro(double value) {
        this.value = value;
    }
    public Currency getCurrency() {
        return Currency.EUR;
    }

    @Override
    public double getValue() { return value;  }

    @Override
    public String getName() { return name();  }
}


enum Krona implements Coin {
    EN_KRONA(1),
    FEM_KRONA(5),
    KVÅ_KRONA(2),
    TIO_KRONA(10);

    double value;
    Krona(double value) {
        this.value = value;
    }
    public Currency getCurrency() {
        return Currency.SEK;
    }

    @Override
    public double getValue() { return value;  }

    @Override
    public String getName() {  return name();  }
}


class ExchangeServiceImpl implements IExchangeService {

    @Override
    public Stream<Coin> exchange(Stream<Coin> stream, Currency from, Currency to) {
        double exchangeRate = getExchangeRate(from, to);
        double sum = stream.mapToDouble(Coin::getValue).sum();
        sum = sum * exchangeRate;

        List<Coin> result = new LinkedList<>();

        List<Coin> coins = Stream.of(getValues(to))
                .sorted(Coin.sortByValueDesc())
                .collect(Collectors.toList());

        for (Coin coin : coins) {
            int count = (int) Math.round(Math.floor(sum / coin.getValue()));
            sum = sum - count * coin.getValue();
            IntStream.range(0, count).forEach(value -> result.add(coin));
        }

        return result.stream();
    }

    private double getExchangeRate(Currency from, Currency to) {
        if (from.equals(to))
            return 1;
        if (from.equals(Currency.EUR) && to.equals(Currency.SEK))
            return 10;
        if (from.equals(Currency.SEK) && to.equals(Currency.EUR))
            return 0.1;
        throw new RuntimeException("Unknown currencies "+from+" "+to);
    }

    private Coin[] getValues(Currency to) {
        return switch (to) {
            case EUR -> Euro.values();
            case SEK -> Krona.values();
            default -> throw new RuntimeException("Internal error");
        };
    }
}
