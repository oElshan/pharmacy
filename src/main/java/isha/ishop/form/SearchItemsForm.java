package isha.ishop.form;

public class SearchItemsForm {
    private String searchName;

    public SearchItemsForm(String searchName) {
        this.searchName = searchName;
    }

    public SearchItemsForm() {
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public String toString() {
        return "SearchItemsForm{" +
                "search='" + searchName + '\'' +
                '}';
    }
}
