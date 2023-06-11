import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddIfAmountNegative() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(-3_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldAddIfAmountZero() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.add(0);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotAddIfBalanceAboveCreditLimit() {
        CreditAccount account = new CreditAccount(2_000, 5_000, 15);
        account.add(3500);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayIfBalanceNotAboveCreditLimit() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());

    }

    @Test
    public void shouldNotPayIfBalanceUnderCreditLimit() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.pay(7000);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountNegative() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.pay(-7000);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountZero() {
        CreditAccount account = new CreditAccount(1000, 5_000, 15);
        account.pay(0);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldCountYearRateIfBalancePositive() {
        CreditAccount account = new CreditAccount(0, 0, 15);
        int balance = 2000;
        int rate = 15;
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldCountYearRateIfBalanceNegative() {
        CreditAccount account = new CreditAccount(0, 0, 15);
        account.setBalance(-2000);
        int rate = 15;
        Assertions.assertEquals(-300, account.yearChange());
    }

    @Test
    public void shouldThrowExceptionIfCreditLimitUnderZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CreditAccount(0, -100, 15));
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceUnderZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(-200, 600, 15));
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceMoreThanCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(2000, 1000, 15));
    }

}