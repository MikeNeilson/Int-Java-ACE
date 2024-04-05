package cocontravar;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class TaxPrepCompany {
  public static void prepareTaxes(Taxable t) {}


  // <? extends Object> has shorthand: <?>

//  public static <E extends Taxable> void prepareBulkTaxes(List<E> lt) {
  public static void prepareBulkTaxes(List<? extends Taxable> lt) {
//    E e = new Taxable();
//    E e = new Individual();
//    E e = new Corporation();
    for (Taxable t : lt) {
      prepareTaxes(t);
    }
  }

  public static void collectNewClientsForJoe(List<? super Individual> li) {
//    Individual i = li.get(0); // ask i's friends
    li.add(new Individual());
    li.add(new Individual());
    li.add(new Individual());
  }

  public static void main(String[] args) {
    List<Taxable> clients = new ArrayList<>();
    clients.addAll(List.of(
        new Corporation(),
        new Corporation(),
        new Individual(),
        new Charity(),
        new Individual()
    ));
//    collectNewClientsForJoe(clients);

    prepareBulkTaxes(clients);

    List<Individual> joesClients = new ArrayList<>();
    joesClients.addAll(List.of(
        new Individual(),
        new Individual(),
        new Individual()
    ));

    prepareBulkTaxes(joesClients);
    prepareBulkTaxes(new ArrayList<Charity>());
    prepareBulkTaxes(new ArrayList<Corporation>());
  }
}
