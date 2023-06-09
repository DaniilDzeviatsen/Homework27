package hw27;

import hw27.ShowRepository.ShowRepository;
import hw27.ShowService.ShowService;
import hw27.models.Show;

import java.util.List;
import java.util.Scanner;

public class Homework27 {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Homework27.class);

    public static void main(String[] args) {
        log.info("Программа запущена");
        ShowRepository showRepository = new ShowRepository();
        ShowService showService = new ShowService(showRepository);
        try {
            List<Show> showlist = showRepository.listShows();
            for (Show show : showlist) {
                System.out.println(show.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        System.out.println("Which  sorting do you  want to implement?");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose operation");
            System.out.println("1) Sorting");
            System.out.println("2) Filtration");
            int operation = Integer.parseInt(sc.nextLine());
            if (operation == 1) {
                System.out.println("Write type of sorting");
                String typeOfSorting = sc.nextLine();
                switch (typeOfSorting) {
                    case "by name" -> showService.handleSortByName();
                    case "by rating" -> showService.handleSortByRating();
                    case "by year" -> showService.handleSortByYear();
                    case "by votes" -> showService.handleSortByVotes();
                    default -> showRepository.listShows();
                }
            }
            if (operation == 2) {
                System.out.println("Choose filters");
                System.out.println("1)Country");
                System.out.println("2)Rating");
                System.out.println("3)Year");
                System.out.println("4)Number of votes");
                System.out.println("5)Key letters");
                int filter = Integer.parseInt(sc.nextLine());
                switch (filter) {
                    case 1 -> {
                        System.out.println("Enter country code");
                        String countryCode = sc.nextLine();
                        showService.handleCountryFilter(countryCode);
                    }
                    case 2 -> {
                        System.out.println("Enter rating from");
                        String rateFrom = sc.nextLine();
                        System.out.println("Enter rating to");
                        String rateTo = sc.nextLine();
                        showService.handleRateFilter(rateFrom, rateTo);
                    }
                    case 3 -> {
                        System.out.println("Enter year");
                        int year = Integer.valueOf(sc.nextLine());
                        showService.handleYearFilter(year);
                    }
                    case 4 -> {
                        System.out.println("Enter number of votes from");
                        String numFrom = sc.nextLine();
                        System.out.println("Enter rating to");
                        String numTo = sc.nextLine();
                        showService.handleVotesFilter(numFrom, numTo);
                    }
                    case 5 -> {
                        System.out.println("Enter line");
                        String query = sc.nextLine();
                        showService.handleTitleFilter(query);
                    }
                }
            }
        } while (true);
    }
}
