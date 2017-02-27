package codewizards.com.ua.gallery.util.converters;

import java.util.List;

/**
 * Created by dmikhov on 01.02.2017.
 */
public interface IConverter<UI, NOT_UI> {
    UI convertToUi(NOT_UI item);
    List<UI> convertToUi(List<NOT_UI> items);
    NOT_UI convertFromUi(UI item);
    List<NOT_UI> convertFromUi(List<UI> items);
}
