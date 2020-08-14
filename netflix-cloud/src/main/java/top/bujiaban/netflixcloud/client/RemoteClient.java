package top.bujiaban.netflixcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rx.Single;

@FeignClient(name = "remoteClient", url = "127.0.0.1:8081")
public interface RemoteClient {

    /**
     * ##远端调用失败##
     * feign.hystrix.enabled=false时，FeignException
     * message:
     * status 400 reading InvokeClient#name(String); content:
     * {"timestamp":1503656322499,
     * "status":400,
     * "error":"Bad Request",
     * "exception":"net.shadow.springcloud.exception.InvalidIdException",
     * "message":"This id is invalid.",
     * "path":"/user/1/username"
     * }.
     * <p>
     * feign.hystrix.enabled=true时：HystrixRuntimeException
     * message:
     * InvokeClient#name(String) failed and no fallback available.
     * <p>
     * 这里如果方法声明InvalidIdException（RuntimeException）无用，并不会主动接收且封装远端的异常。
     * 顶多用做声明式异常，显式提醒、强制调用者强制处理异常。
     */
    @GetMapping("/users/{id}/username")
    public String name(@PathVariable("id") String id);

    @GetMapping("/users/{id}/username")
    public Single<String> nameBySingle(@PathVariable("id") String id);

}
