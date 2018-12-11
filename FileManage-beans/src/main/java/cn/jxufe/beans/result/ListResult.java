package cn.jxufe.beans.result;



import cn.jxufe.beans.enums.Errcode;
import com.github.pagehelper.PageInfo;

/**
 * 分页返回结果
 * @param <T>
 */
public class ListResult<T> extends BaseResult {

    private PageInfo<T> page;


    public static <T> ListResult<T> buildSuccess(PageInfo<T> page) {
        ListResult<T> result = new ListResult<>();
        result.setPage(page);
        result.setCode(Errcode.A00006.toString());

        return result;
    }


    public PageInfo<T> getPage() {
        return page;
    }

    public void setPage(PageInfo<T> page) {
        this.page = page;
    }
}




