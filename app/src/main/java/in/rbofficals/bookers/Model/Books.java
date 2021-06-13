package in.rbofficals.bookers.Model;

public class Books {
    private String title, url, description;

    public Books(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public Books() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
