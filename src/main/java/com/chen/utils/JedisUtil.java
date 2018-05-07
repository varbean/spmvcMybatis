package com.chen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。 如果key不存在，APPEND就简单地将给定key设为value，就像执行SET key value一样。
     *
     * @param key key
     * @param value value
     * @return 值
     */
    public Long append(String key, String value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.append(key, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 将key中储存的数字值减一。 如果key不存在，以0为key的初始值，然后执行DECR操作。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     *
     * @param key key
     * @return 值
     */
    public Long decr(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.decr(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 将key所储存的值减去减量integer。 如果key不存在，以0为key的初始值，然后执行DECRBY操作。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在64位(bit)有符号数字表示之内。
     *
     * @param key key
     * @param integer integer
     * @return 值
     */
    public Long decrBy(String key, long integer) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.decrBy(key, integer);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 移除给定的一个或多个key。 如果key不存在，则忽略该命令。
     *
     * @param key key
     * @return Long
     */
    public Long del(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 检查给定key是否存在。
     * <p>
     *
     * @param key key
     * @return 是否
     */
    public Boolean exists(String key) {
        ShardedJedis shardedJedis = null;
        Boolean ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.exists(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 为给定key设置生存时间。单位秒 当key过期时，它会被自动删除。
     *
     * @param key key
     * @param seconds  seconds
     * @return Long
     */
    public Long expire(String key, int seconds) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.expire(key, seconds);
            return ret;
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 以UNIX时间戳为key设置生存时间。 EXPIREAT命令接受的时间参数是UNIX时间戳(unix timestamp)。
     *
     * @param key key
     * @param unixTime unixTime
     * @return Long
     */
    public Long expireAt(String key, long unixTime) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.expireAt(key, unixTime);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 返回key所关联的字符串值
     * <p>
     * 假如key储存的值不是字符串类型，返回一个错误，因为GET只能用于处理字符串值
     *
     * @param key key
     * @return String
     */
    public String get(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 将给定key的值设为value，并返回key的旧值。 当key存在但不是字符串类型时，返回一个错误。
     *
     * @param key key
     * @param value value
     * @return value
     */
    public String getSet(String key, String value) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.getSet(key, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 返回key中字符串值的子字符串，字符串的截取范围由startOffset和endOffset两个偏移量决定(包括startOffset和endOffset在内)。
     *
     * @param key key
     * @param startOffset startOffset
     * @param endOffset endOffset
     * @return 子字符串
     */
    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.getrange(key, startOffset, endOffset);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }

    /**
     * 删除哈希表key中的一个指定域，不存在的域将被忽略
     *
     * @param key key
     * @param field field
     * @return Long
     */
    public Long hdel(String key, String field) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hdel(key, field);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Boolean hexists(String key, String field) {
        ShardedJedis shardedJedis = null;
        Boolean ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hexists(key, field);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String hget(String key, String field) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hget(key, field);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Map<String, String> hgetAll(String key) {
        ShardedJedis shardedJedis = null;
        Map<String, String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hgetAll(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long hincrBy(String key, String field, long value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hincrBy(key, field, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> hkeys(String key) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hkeys(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long hlen(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hlen(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public List<String> hmget(String key, String... fields) {
        ShardedJedis shardedJedis = null;
        List<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hmget(key, fields);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String hmset(String key, Map<String, String> hash) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hmset(key, hash);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long hset(String key, String field, String value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long hsetnx(String key, String field, String value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hsetnx(key, field, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public List<String> hvals(String key) {
        ShardedJedis shardedJedis = null;
        List<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.hvals(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long incr(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.incr(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long incrBy(String key, long integer) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.incrBy(key, integer);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String lindex(String key, long index) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lindex(key, index);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long llen(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.llen(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String lpop(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lpop(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long lpush(String key, String string) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lpush(key, string);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public List<String> lrange(String key, long start, long end) {
        ShardedJedis shardedJedis = null;
        List<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long lrem(String key, long count, String value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lrem(key, count, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String lset(String key, long index, String value) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.lset(key, index, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String ltrim(String key, long start, long end) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.ltrim(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String rpop(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.rpop(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long rpush(String key, String string) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.rpush(key, string);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long sadd(String key, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.sadd(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long scard(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.scard(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String set(String key, String value) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.set(key, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String setex(String key, int seconds, String value) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long setnx(String key, String value) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.setnx(key, value);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public long setrange(String key, long offset, String value) {
        ShardedJedis shardedJedis = null;
        long ret = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.setrange(key, offset, value);
            return ret;
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Boolean sismember(String key, String member) {
        ShardedJedis shardedJedis = null;
        Boolean ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> smembers(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            Set<String> ret = shardedJedis.smembers(key);
            return ret;
        } catch (Exception e) {
            logger.error("redis error:", e);
            return null;
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }


    public List<String> sort(String key) {
        ShardedJedis shardedJedis = null;
        List<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.sort(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String spop(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.spop(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String srandmember(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.srandmember(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long srem(String key, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.srem(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String substr(String key, int start, int end) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.substr(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long ttl(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.ttl(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public String type(String key) {
        ShardedJedis shardedJedis = null;
        String ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.type(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zadd(String key, double score, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zadd(key, score, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zcard(String key) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zcard(key);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zcount(String key, double min, double max) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zcount(key, min, max);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Double zincrby(String key, double score, String member) {
        ShardedJedis shardedJedis = null;
        Double ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zincrby(key, score, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrange(String key, int start, int end) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrangeByScore(String key, double min, double max) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrangeByScore(String key, double min, double max,
                                     int offset, int count) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrangeByScore(key, min, max, offset, count);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zrank(String key, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrank(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zrem(String key, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrem(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zremrangeByRank(String key, int start, int end) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zremrangeByRank(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zremrangeByScore(String key, double start, double end) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zremrangeByScore(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrevrange(String key, int start, int end) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrevrangeByScore(String key, double max, double min) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrevrangeByScore(key, max, min);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Set<String> zrevrangeByScore(String key, double max, double min,
                                        int offset, int count) {
        ShardedJedis shardedJedis = null;
        Set<String> ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Long zrevrank(String key, String member) {
        ShardedJedis shardedJedis = null;
        Long ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zrevrank(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }


    public Double zscore(String key, String member) {
        ShardedJedis shardedJedis = null;
        Double ret = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            ret = shardedJedis.zscore(key, member);
        } catch (Exception e) {
            logger.error("redis error:", e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return ret;
    }



}
