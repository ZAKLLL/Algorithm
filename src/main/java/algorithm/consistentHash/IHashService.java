package algorithm.consistentHash;

/**
 * @Classname IHashServie
 * @Description 一致性hash算法接口
 * @Date 2020/8/13 10:17
 * @Created by ZhangJiaKui
 */
public interface IHashService {
    Long hash(String key);
}
