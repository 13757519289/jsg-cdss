package com.jsg.dao.mysql;

import com.jsg.entity.DictionaryCatalog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jeanson 进生
 * @date 2019/10/8 19:54
 */
@Repository
public interface DictionaryMapper {
    List<DictionaryCatalog> search(DictionaryCatalog dictionaryCatalog);

}
