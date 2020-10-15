package top.bujiaban.pinpoint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "remoteClient", url = "localhost:8989")
public interface RemoteHttpClient {

    @GetMapping("/cachedUsers/{id}/username")
    String username(@PathVariable("id") String id);
}
