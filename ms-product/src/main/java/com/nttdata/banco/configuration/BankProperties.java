package com.nttdata.banco.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Getter
@Setter
public class BankProperties {
    private Accounts accounts;
    private Credits credits;

    @Getter
    @Setter
    public static class Accounts {
        private AccountDetails savings;
        private AccountDetails current;
        private FixedDepositAccountDetails fixedDeposit;

        @Getter
        @Setter
        public static class AccountDetails {
            private boolean maintenanceFee;
            private int maxMonthlyTransactions;
        }

        @Getter
        @Setter
        public static class FixedDepositAccountDetails extends AccountDetails {
            private int specificTransactionDay;
        }
    }

    @Getter
    @Setter
    public static class Credits {
        private CreditDetails personal;
        private CreditDetails business;
        private CreditCard creditCard;

        @Getter
        @Setter
        public static class CreditDetails {
            private int maxCredits;
        }

        @Getter
        @Setter
        public static class CreditCard {
            private boolean personalAllowed;
            private boolean businessAllowed;
        }
    }
}
