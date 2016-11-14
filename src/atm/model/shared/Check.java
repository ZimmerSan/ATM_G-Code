
package atm.model.shared;

public abstract class Check {

    private String[] headingPortion;
    protected String[] detailsPortion;
    private String balancesPortion;

//    protected Check(Atm atm, Card card, Transaction transaction, Money balance) {
//        // Heading portion of the receipt
//
//        headingPortion = new String[4];
//        headingPortion[0] = new Date().toString();
//        headingPortion[1] = atm.getBankName();
//        headingPortion[3] = "CARD " + card.getNumber() + " TRANS #" + transaction.getSerialNumber();
//
//        // The constructor for each subclass will fill in the details array
//        // appropriately
//
//        // Balances portion of the receipt
//
//        balancesPortion = "TOTAL BAL: " + balance;
//    }

    /** Get the individual lines to be printed.  Each call to the nextElement()
     *  of the enumeration gets one line (as a String)
     */

//    public Enumeration getLines()
//    {
//        return new Enumeration() {
//
//            // The current portion of the receipt being printed
//
//            private int portion = 0;
//
//            // The index of the line in the current portion
//
//            private int index = 0;
//
//            public boolean hasMoreElements()
//            {
//                return portion < 2 || index < balancesPortion.length;
//            }
//
//            public Object nextElement()
//            {
//                String line = null;
//
//                switch (portion)
//                {
//                    case 0:
//
//                        line = headingPortion[index ++];
//                        if (index >= headingPortion.length)
//                        {
//                            portion ++;
//                            index = 0;
//                        }
//                        break;
//
//                    case 1:
//
//                        line = detailsPortion[index ++];
//                        if (index >= detailsPortion.length)
//                        {
//                            portion ++;
//                            index = 0;
//                        }
//                        break;
//
//                    case 2:
//
//                        line = balancesPortion[index ++];
//                        break;
//                }
//                return line;
//            }
//        };
//    }

}