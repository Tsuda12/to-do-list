package tsuda.br.com.to_do_list.security;

import lombok.Builder;

@Builder
public record JWTUserData(
        String userId,
        String email,
        String role
) {}