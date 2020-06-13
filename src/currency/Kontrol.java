// MEHMET https://github.com/SouL-H/
package currency;

import com.github.barismeral.dovizAPI.Currency;
import com.github.barismeral.dovizAPI.CurrencyFactory;
import com.github.barismeral.dovizAPI.Moneys;

public class Kontrol {
        static CurrencyFactory factory = new CurrencyFactory(Moneys.US_DOLLAR);
        static Currency cur = factory.getCurrency(); 
        static CurrencyFactory factory1 = new CurrencyFactory(Moneys.EURO);
        static Currency cur1 = factory1.getCurrency();
        static CurrencyFactory factory2 = new CurrencyFactory(Moneys.KUWAITI_DINAR);
        static Currency cur2 = factory2.getCurrency();    
        static float buying,eskialis;
        static float selling,eskisatis;
        static float fark;
        
        
        
        public String cDolar() {
          

        buying = cur.getBuyingPrice(); 
        eskialis = buying;
        selling = cur.getSellingPrice(); 
        eskisatis = selling;
        if(eskialis>buying){
            return "Azalis";
        }
        else if(eskialis==buying){
            return "Aynı";
        }
        else {
             return "Artis";
                    }
        }
        
         public String cEuro() {
          

        buying = cur1.getBuyingPrice();
        eskialis = buying;
        selling = cur1.getSellingPrice();  
        eskisatis = selling;
        if(eskialis>buying){
            return "Azalis";
        }
        else if(eskialis==buying){
            return "Aynı";
        }
        else {
             return "Artis";
                    }
        }
         
        public String cDinar() {
          

        buying = cur2.getBuyingPrice(); 
        eskialis = buying;
        selling = cur2.getSellingPrice();  
        eskisatis = selling;
        if(eskialis>buying){
            return "Azalis";
        }
        else if(eskialis==buying){
            return "Aynı";
        }
        else {
             return "Artis";
                    }
        } 
    }
    
