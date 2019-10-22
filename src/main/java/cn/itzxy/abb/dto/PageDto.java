package cn.itzxy.abb.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<PublishDto> publishDtoList;
    private int page;
    private int size;
    private boolean previousPage;
    private boolean nextPage;
    private boolean firstPage;
    private boolean lastPage;
    private int allPage;
    private int allCount;
    private List<Integer> pages=new ArrayList<>();
    public void setPage(int totalCount,int page,int size){
        if(totalCount%size==0){
            allPage= totalCount/size;
        }
        else{
            allPage=(totalCount/size)+1;
        }
        if(page<1){
            page=1;
        }
        if(page>allPage){
            page=allPage;
        }
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
              pages.add(0,page-i);
            }
            if(page+i<=allPage){
                pages.add(page+i);
            }

        }
        if(page==1){
            previousPage=false;
            firstPage=false;
        }
        else{
            previousPage=true;
            firstPage=true;
        }
        if(page==allPage){
            lastPage=false;
            nextPage=false;
        }
        else{
            lastPage=true;
            nextPage=true;
        }

    }


}
