package LibrarySystem;

import java.util.Iterator;

public class Catalog implements Iterable{
    private static final int MAX_NUMBER=1000;
    private CatalogItem[] items=new CatalogItem[MAX_NUMBER];
    private int length=0;

    public void addItem(CatalogItem item){
        if (length>=MAX_NUMBER){
            System.out.println("Too much items now!Addition failed!");
        }else {
            items[length]=item;
            length++;
        }
    }
    public void removeItem(CatalogItem item){
        if (length==0){
            System.out.println("No item now!Delete failed!");
        }else {
            int pos=-1;
            for (int i=0;i<getNumberOfItems();++i){
                if (items[i].equals(item)){
                    pos=i;
                    break;
                }
            }
            if (pos==-1){
                System.out.println("Don't have such an Object!");
            }else {
                for (int i=pos+1;i<getNumberOfItems();++i){
                    items[i-1]=items[i];
                }
                length--;
                System.out.println("Item deleted!");
            }
        }
    }
    public CatalogItem getItem(String code){
        for (int i=0;i<getNumberOfItems();++i){
            if (items[i].getTitle().equals(code)){
                return items[i];
            }
        }
        return null;
    }
    public CatalogItem getItem(int index){
        if (index>=getNumberOfItems() || index<0){
            return null;
        }
        return items[index];
    }
    public int getNumberOfItems(){
        return length;
    }


    @Override
    public Iterator<CatalogItem> iterator() {
        return new Iterator<CatalogItem>() {
            private int index=0;

            @Override
            public boolean hasNext() {
                return (index < getNumberOfItems());
            }

            @Override
            public CatalogItem next() {
                index++;
                return items[index];
            }
        };
    }
}
