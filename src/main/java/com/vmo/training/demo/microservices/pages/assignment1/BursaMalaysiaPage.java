package com.vmo.training.demo.microservices.pages.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.vmo.training.demo.models.assignment1.Counter;
import com.vmo.training.demo.utils.keywords.WebUI;
import org.openqa.selenium.WebElement;

public class BursaMalaysiaPage extends WebUI {
    List<Counter> listCounter = new ArrayList<Counter>();
    List<Counter> newList = new ArrayList<Counter>();

    public void getCounter() {
        List<WebElement> list = findElements("//table[@class='table table-striped table-borderless table-carousel plain-table']/tbody/tr");
        List<WebElement> codes = findElements("//table[@class='table table-striped table-borderless table-carousel plain-table']/tbody/tr/td[1]");
        List<WebElement> names = findElements("//table[@class='table table-striped table-borderless table-carousel plain-table']/tbody/tr/th");
        List<WebElement> changes = findElements("//table[@class='table table-striped table-borderless table-carousel plain-table']/tbody/tr/td[3]");
        List<WebElement> volumes = findElements("//table[@class='table table-striped table-borderless table-carousel plain-table']/tbody/tr/td[4]");

        for (int i = 0; i < list.size(); i++) {
            Counter counter = new Counter();
            counter.stockCode = codes.get(i).getText().trim();
            counter.stockName = names.get(i).getText().trim();
            counter.change = changes.get(i).getText().trim();
            counter.volume = volumes.get(i).getText().trim();
            listCounter.add(counter);
        }
    }

    public void sort() {
        List<Counter> negativeNumbers = new ArrayList<Counter>();
        List<Counter> positiveNumbers = new ArrayList<Counter>();

        Collections.sort(listCounter, new Comparator<Counter>() {
            @Override
            public int compare(Counter counter1, Counter counter2) {
                if (convert(counter1.volume.replace(",", "")) < convert(counter2.volume.replace(",", ""))) {
                    return 1;
                } else {
                    if (convert(counter1.volume.replace(",", "")) == convert(counter2.volume.replace(",", ""))) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });

        for (int i = 0; i < listCounter.subList(0, 10).size(); i++) {
            if (listCounter.get(i).change.contains("-")) {
                negativeNumbers.add(listCounter.get(i));
//			for(int j=0;j<negativeNumbers.size();j++) {
//				if(negativeNumbers.get(j).change.trim().equals("-")) {
//					newList.add(negativeNumbers.get(j));
//					negativeNumbers.remove(j);
//				}
//			}

            } else {
                positiveNumbers.add(listCounter.get(i));
            }
        }
        Collections.sort(negativeNumbers, new Comparator<Counter>() {
            @Override
            public int compare(Counter c1, Counter c2) {
                if (convert(c1.change.replace("-", "").replace(".", "")) < convert(c2.change.replace("-", "").replace(".", ""))) {
                    return 1;
                } else {
                    if (convert(c1.change.replace("-", "").replace(".", "")) == convert(c2.change.replace("-", "").replace(".", ""))) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        for (Counter counter1 : negativeNumbers) {
            newList.add(counter1);
        }

        Collections.sort(positiveNumbers, new Comparator<Counter>() {
            @Override
            public int compare(Counter c1, Counter c2) {
                if (convert(c1.change.replace("+", "").replace(".", "")) > convert(c2.change.replace("+", "").replace(".", ""))) {
                    return 1;
                } else {
                    if (convert(c1.change.replace("+", "").replace(".", "")) == convert(c2.change.replace("+", "").replace(".", ""))) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });

        for (Counter counter2 : positiveNumbers) {
            newList.add(counter2);
        }

        Collections.reverse(newList);
    }

    public void showCounters() {
        sort();
        System.out.println("\n\nCounter List\n");
        for (Counter counter : newList) {
            System.out.println("Code: " + counter.stockCode + "\tName: " + counter.stockName + "\tChange: " + counter.change + "\tVolume: " + counter.volume);

        }
    }

}
