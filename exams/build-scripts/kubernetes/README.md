Before starting the app make sure that `challenges-demo` namespace exists. Checking:

```shell
~ kubectl get namespaces
NAME              STATUS   AGE
challenges-demo   Active   27s
default           Active   53d
kube-node-lease   Active   53d
kube-public       Active   53d
kube-system       Active   53d
```

If not exists, create using the following command: 

```shell
~ kubectl create namespace challenges-demo
```

---

Enable port-forwarding to the main app (if something went wrong):

```shell
~ kubectl port-forward deployment/challenges-provider 8080:8080
```

Should see something like: 

```shell
Forwarding from 127.0.0.1:8080 -> 8080
Forwarding from [::1]:8080 -> 8080
Handling connection for 8080
```

---

Set the default namespace for convenience:

```shell
~ kubectl config set-context --current --namespace=challenges-demo
```