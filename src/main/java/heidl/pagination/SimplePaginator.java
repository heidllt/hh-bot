package heidl.pagination;


public class SimplePaginator {

    int maxPage;
    int curPage;

    static SimplePaginator instance;

    private SimplePaginator(){};

    public static SimplePaginator getInstance() {
        if ( instance == null ) {
            instance = new SimplePaginator();
        }
        return instance;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setMaxPage(int maxPage) {
        if (this.maxPage < maxPage) {
            this.maxPage = maxPage;
        }
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public boolean hasNext() {
        return curPage + 1 <= maxPage;
    }

    public Integer next() {
        return curPage + 1;
    }
}
