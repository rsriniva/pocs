package org.switchyard.quickstarts.jca.outbound;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

/**
 * OrderServiceBean.
 * 
 * @author <a href="mailto:tm.igarashi@gmail.com">Tomohisa Igarashi</a>
 *
 */
@Service(OrderService.class)
public class OrderServiceBean implements OrderService {

    @Inject @Reference("ShippingReference")
    private OrderService _shipping;
    @Inject @Reference("FillingStockReference")
    private OrderService _fillingStock;
    
    private List<String> _stock = Arrays.asList("BREAD", "BUTTER", "JAM", "EGG", "MILK");

    private int sleepMillis = 1000;
    
    @Override
    public void process(String order) {
        System.out.println("OrderServiceBean.process() order = "+order);
        try {
            Thread.sleep(sleepMillis);
        } catch(Exception x) {
            x.printStackTrace();
        }
        if (_stock.contains(order)) {
            _shipping.process(order);
        } else {
            _fillingStock.process(order);
        }
    }

}
